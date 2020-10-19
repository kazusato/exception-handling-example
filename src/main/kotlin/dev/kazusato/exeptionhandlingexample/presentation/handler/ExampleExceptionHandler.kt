package dev.kazusato.exeptionhandlingexample.presentation.handler

import dev.kazusato.exeptionhandlingexample.domain.exception.AbstractBusinessRuleException
import dev.kazusato.exeptionhandlingexample.extsys.exception.AbstractExtsysException
import dev.kazusato.exeptionhandlingexample.presentation.dto.BaseResponseDto
import dev.kazusato.exeptionhandlingexample.presentation.dto.ProcessStatus
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.IllegalArgumentException

@RestControllerAdvice
class ExampleExceptionHandler : ResponseEntityExceptionHandler() {

	companion object {
		private val logger = LoggerFactory.getLogger(this::class.java.enclosingClass)
	}

	// Bean Validationでエラーになった場合、MethodArgumentNotValidExceptionがスローされる。
	override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
		val msg = "Request validation failed."
		// 例外情報のロギングは、ここで統一的に行う（そうしないと、1つの事象に対して、あちこちでログが出て、解析が困難になる）
		// ログ出力時に例外オブジェクトを渡すことで、スタックトレースを出力できる（これを忘れると、スタックトレースがログに出力されず、解析が困難になる）
		//
		// ログレベルは、この場合、API呼び出し元が誤ったデータを送信すると発生しうる事象であり、この例外がスローされたからといって
		// システム全体が異常な状態にあるとはいえないことから、WARNとしている。
		logger.warn(msg, ex)
		// 応答電文に、例外のメッセージをそのまま詰めてはいけない（公開すべきでない内部情報を漏洩してしまう可能性があるため）。
		// API呼び出し元に、どの項目が不適切かを応答メッセージに詰めて返却したほうが、呼び出し元に優しいのだが、適切に行うのは意外と大変。
		//
		// ex.parameterには、「どのメソッドの何個目の引数か」という情報が入っているため、これを応答メッセージに入れても、受け取りてはうれしくない。
		// Bean Validationのチェック結果は、ex.bindingResultに入っているが、DTO（クラス）の情報ベースで（電文ベースではなく）出力されるため、
		// これを出力すると、内部情報を公開しすぎの感がある。
		// そうなると、一般的なメッセージ（項目やエラー内容に言及しない）を返すしかなくなる。
		// ただし、その場合でも、ログ（スタックトレース）にはメッセージがすべて出力される。
		//
		// また、エラーメッセージの言語をどうするか、というのも考えるべきポイント。
		// UIであれば、メッセージを多言語化しておき、リクエスト時の言語設定（HTTPヘッダーのAccept-Language）からロケールを判定して返却することが可能。
		// しかし、システム間連携のAPIに対しては、Accept-Languageなど設定しないので意味がない。
		// UIから呼ぶAPIであっても、APIがエラーを返す時点で想定された挙動ではないはずで、そのメッセージがUIの利用者に有意義なことはあまりなさそう。
		// こういう観点を踏まえると、APIのエラーメッセージは英語のみで返しておくのがよいのではないかと思う。
		// （日本語でエラーメッセージを設定すると、日本語を理解する呼び出し元以外には、情報が得られないことになる）
		return handleExceptionInternal(ex, BaseResponseDto(ProcessStatus.ERROR, msg), headers, status, request)
	}

	// APIリクエストのJSONがパースできない場合、HttpMessageNotReadableExceptionがスローされる。
	override fun handleHttpMessageNotReadable(ex: HttpMessageNotReadableException, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
		val msg = "Request is not readable."
		logger.warn(msg, ex)
		return handleExceptionInternal(ex, BaseResponseDto(ProcessStatus.ERROR, msg), headers, status, request)
	}

	// IllegalArgumentExceptionでは、応答ステータスを400（Bad Request）にする。
	@ExceptionHandler(IllegalArgumentException::class)
	fun handleIllegalArgumentException(ex: IllegalArgumentException, request: WebRequest): ResponseEntity<Any> {
		val msg = "Request contains inappropreate values."
		logger.warn(msg, ex)
		return handleExceptionInternal(ex, BaseResponseDto(ProcessStatus.ERROR, msg), HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request)
	}

	// 業務例外の基底クラスであるAbstractBusinessRuleExceptionに対する処理。応答ステータスを400（Bad Request）とし、
	// 例外オブジェクトに設定されたメッセージを応答電文に設定する。
	// ※独自に作成した例外クラスであり、スローする際には応答電文に含められるメッセージとする実装ルールとしている前提。
	// ログにはより詳細な（応答電文に含めるのが不適切な）情報を出したいのであれば、応答電文用メッセージとログ用メッセージを
	// 分けて格納できるよう基底クラスを作っておく手もある。
	@ExceptionHandler(AbstractBusinessRuleException::class)
	fun handleBusinessRuleException(ex: AbstractBusinessRuleException, request: WebRequest): ResponseEntity<Any> {
		logger.warn(ex.message, ex)
		return handleExceptionInternal(ex, BaseResponseDto(ProcessStatus.ERROR, ex.message), HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request)
	}

	@ExceptionHandler(AbstractExtsysException::class)
	fun handleExtsysException(ex: AbstractExtsysException, request: WebRequest): ResponseEntity<Any> {
		if (ex.cause != null && ex.cause is HttpServerErrorException) {
			logger.error(ex.message, ex)
			return handleExceptionInternal(ex, BaseResponseDto(ProcessStatus.ERROR, "Unexpected error returned from external system: ${ex.targetSystem}"),
					HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, request)
		} else if (ex != null && ex.cause is HttpClientErrorException){
			logger.warn(ex.message, ex)
			return handleExceptionInternal(ex, BaseResponseDto(ProcessStatus.ERROR, "Request is rejected by external system: ${ex.targetSystem}"),
					HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request)
		} else {
			logger.warn(ex.message, ex)
			return handleExceptionInternal(ex, BaseResponseDto(ProcessStatus.ERROR, "Error occurred during external system invocation: ${ex.targetSystem}"),
					HttpHeaders.EMPTY, HttpStatus.BAD_REQUEST, request)
		}
	}

	// 明示的にハンドリングしていない例外は、ここで処理される。
	@ExceptionHandler(Exception::class)
	fun handleGenericException(ex: Exception, request: WebRequest): ResponseEntity<Any> {
		val msg = "Unkown exception occurred."
		logger.error(msg, ex)
		return handleExceptionInternal(ex, BaseResponseDto(ProcessStatus.ERROR, msg), HttpHeaders.EMPTY, HttpStatus.INTERNAL_SERVER_ERROR, request)
	}
}
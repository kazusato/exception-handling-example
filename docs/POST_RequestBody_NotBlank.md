# Request

```
$ curl -X POST -H "Content-Type: application/json" -d '{"title": "Hello World", "author": "Programmer", "publisher": ""}' http://localhost:8080/book
{"timestamp":"2020-10-17T08:59:45.053+00:00","status":400,"error":"Bad Request","message":"","path":"/book"}
```

# Log

```
2020-10-17 17:59:45.051  WARN 164551 --- [nio-8080-exec-2] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.web.bind.MethodArgumentNotValidException: Validation failed for argument [0] in public dev.kazusato.exeptionhandlingexample.presentation.dto.BookRegistrationResponseDto dev.kazusato.exeptionhandlingexample.presentation.api.BookApi.registerBook(dev.kazusato.exeptionhandlingexample.presentation.dto.BookDto): [Field error in object 'bookDto' on field 'publisher': rejected value []; codes [NotBlank.bookDto.publisher,NotBlank.publisher,NotBlank.java.lang.String,NotBlank]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [bookDto.publisher,publisher]; arguments []; default message [publisher]]; default message [空白は許可されていません]] ]
```

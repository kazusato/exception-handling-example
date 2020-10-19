# Request

```
$ curl -X POST -H "Content-Type: application/json" -d '{}' http://localhost:8080/book
{"timestamp":"2020-10-17T08:38:59.585+00:00","status":400,"error":"Bad Request","message":"","path":"/book"}
```

# Log

```
2020-10-17 17:38:59.582  WARN 162856 --- [nio-8080-exec-4] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Instantiation of [simple type, class dev.kazusato.exeptionhandlingexample.presentation.dto.BookDto] value failed for JSON property title due to missing (therefore NULL) value for creator parameter title which is a non-nullable type; nested exception is com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException: Instantiation of [simple type, class dev.kazusato.exeptionhandlingexample.presentation.dto.BookDto] value failed for JSON property title due to missing (therefore NULL) value for creator parameter title which is a non-nullable type
 at [Source: (PushbackInputStream); line: 1, column: 2] (through reference chain: dev.kazusato.exeptionhandlingexample.presentation.dto.BookDto["title"])]
```


# Request

```
$ curl http://localhost:8080/book/list?category

{"timestamp":"2020-10-17T07:15:17.468+00:00","status":500,"error":"Internal Server Error","message":"","path":"/book/list"}
```

# Log

```
2020-10-17 16:15:17.452 ERROR 157170 --- [nio-8080-exec-2] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed; nested exception is java.lang.IllegalArgumentException: No enum constant dev.kazusato.exeptionhandlingexample.domain.model.BookCategory.] with root cause

java.lang.IllegalArgumentException: No enum constant dev.kazusato.exeptionhandlingexample.domain.model.BookCategory.
	at java.base/java.lang.Enum.valueOf(Enum.java:240) ~[na:na]
	at dev.kazusato.exeptionhandlingexample.domain.model.BookCategory.valueOf(BookCategory.kt) ~[main/:na]
	at dev.kazusato.exeptionhandlingexample.domain.model.SearchCriteria.<init>(SearchCriteria.kt:10) ~[main/:na]
	at dev.kazusato.exeptionhandlingexample.presentation.api.BookApi.searchBooks(BookApi.kt:22) ~[main/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190) ~[spring-web-5.2.9.RELEASE.jar:5.2.9.RELEASE]
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138) ~[spring-web-5.2.9.RELEASE.jar:5.2.9.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:105) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:878) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:792) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898) ~[spring-webmvc-5.2.9.RELEASE.jar:5.2.9.RELEASE]
```


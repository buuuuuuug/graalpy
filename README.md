# STEP TO REPRODUCE THE ERROR

## Pre requirements

1. architecture: aarch64
2. OS: Ubuntu 20.04
3. JDK: Oracle GraalVM 23.0.1+11.1  with [static-lib](https://pkgs.alpinelinux.org/package/edge/testing/aarch64/openjdk23-static-libs) placed at $JAVA_HOME/lib/static/linux-aarch64/musl
// this is for static compilation working on aarch64 platform. 
4. musl locally compiled and installed

## step 1:
```shell
git clone https://github.com/buuuuuuug/graalpy.git
```

## step 2:
```shell
./gradlew nativeCompile
```

## step 3:
```shell
./build/native/nativeCompile/graalpy -Xss8M
```

## step 4:
```shell
curl -X GET http://localhost:8080/eval?path=/path/to/demo.py
```

fire a request with absolute path of [demo.py](src/main/python/demo.py)

## ERROR I GOT

```text
Traceback (most recent call last):
  File "Unnamed", line 1, in <module>
  File "/graalpy_vfs/venv/lib/python3.11/site-packages/numpy/__init__.py", line 130, in <module>
    from numpy.__config__ import show as show_config
  File "/graalpy_vfs/venv/lib/python3.11/site-packages/numpy/__config__.py", line 4, in <module>
    from numpy.core._multiarray_umath import (
  File "/graalpy_vfs/venv/lib/python3.11/site-packages/numpy/core/__init__.py", line 24, in <module>
    from . import multiarray
  File "/graalpy_vfs/venv/lib/python3.11/site-packages/numpy/core/multiarray.py", line 10, in <module>
    from . import overrides
  File "/graalpy_vfs/venv/lib/python3.11/site-packages/numpy/core/overrides.py", line 8, in <module>
    from numpy.core._multiarray_umath import (
SystemError: NFIUnsatisfiedLinkError: Dynamic loading not supported
2024-12-23T07:26:49.931Z ERROR 242964 --- [graalPy] [nio-8080-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: org.graalvm.polyglot.PolyglotException] with root cause

org.graalvm.polyglot.PolyglotException: null
	at org.graalvm.polyglot/org.graalvm.polyglot.Context.eval(Context.java:402) ~[na:na]
	at com.chaney.infra.graalpy.endpoint.DemoController.eval(DemoController.java:25) ~[graalpy:na]
	at java.base@23.0.1/java.lang.reflect.Method.invoke(Method.java:580) ~[graalpy:na]
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:257) ~[graalpy:6.2.1]
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:190) ~[graalpy:6.2.1]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:118) ~[graalpy:6.2.1]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:986) ~[graalpy:6.2.1]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:891) ~[graalpy:6.2.1]
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[graalpy:6.2.1]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1088) ~[graalpy:6.2.1]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:978) ~[graalpy:6.2.1]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014) ~[graalpy:6.2.1]
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:903) ~[graalpy:6.2.1]
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564) ~[graalpy:6.0]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885) ~[graalpy:6.2.1]
	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658) ~[graalpy:6.0]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195) ~[na:na]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[na:na]
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51) ~[graalpy:10.1.34]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[na:na]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[na:na]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[graalpy:6.2.1]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[graalpy:6.2.1]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[na:na]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[na:na]
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[graalpy:6.2.1]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[graalpy:6.2.1]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[na:na]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[na:na]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[graalpy:6.2.1]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116) ~[graalpy:6.2.1]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164) ~[na:na]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140) ~[na:na]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167) ~[na:na]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90) ~[na:na]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:483) ~[graalpy:10.1.34]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115) ~[na:na]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93) ~[graalpy:10.1.34]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74) ~[na:na]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344) ~[na:na]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:397) ~[na:na]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63) ~[graalpy:10.1.34]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:905) ~[na:na]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1741) ~[na:na]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52) ~[graalpy:10.1.34]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1190) ~[na:na]
	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659) ~[na:na]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63) ~[na:na]
	at java.base@23.0.1/java.lang.Thread.runWith(Thread.java:1588) ~[graalpy:na]
	at java.base@23.0.1/java.lang.Thread.run(Thread.java:1575) ~[graalpy:na]
	at org.graalvm.nativeimage.builder/com.oracle.svm.core.thread.PlatformThreads.threadStartRoutine(PlatformThreads.java:832) ~[graalpy:na]
	at org.graalvm.nativeimage.builder/com.oracle.svm.core.thread.PlatformThreads.threadStartRoutine(PlatformThreads.java:808) ~[graalpy:na]
```


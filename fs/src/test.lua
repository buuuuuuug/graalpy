require('luajava')
local javaClass = luajava.bindClass("com.chaney.infra.graalpy.endpoint.DemoController")
javaClass:five()
```
MVC

M : Model

V : View

C : Controller -> DispatcherServlet

Front Controller = DispatcherServlet

Application Controller = @Controller or Controller

ServletContextListener -> ContextLoaderListener -> Root WebApplicationContext

						  DispatcherServlet -> Servelt WebApplicationContext
						  
						  Services -> @Service
						  
						  Repositories -> @Repository
						  
DispatcherServlet < FrameworkServlet < HttpServletBean < HttpServlet

自动装配:DispatcherServletAutoConfiguration

ServletCOntext path = "" or "/"

Request URI = ServeltContext path + @RequestMapping("")/@GetMapping

当前例子：

Request URI = "" + "" = "" -> RestDemoController#index()

Request URI : "" 或者 "/"

HandlerMapping ，寻找 Request URI ，匹配的 Handler :
	
	Handler : 是处理的方法，当然这是一种实例
	整体流程 : Request -> Handler -> 执行结果 -> 返回(REST) -> 普通的文本
	
	请求处理映射 : RequestMappingHandlerMapping -> @RequestMapping Handler Mapping
	
	拦截器 : HandlerInterceptor 可以理解 Handler 到底是什么
	
			处理顺序 : preHandler(true) -> Handler : HandlerMethod 执行 (Method#invoke()) -> postHandler -> afterCompletion
					  preHandler(false)
```



```
Spring Web MVC 的配置 Bean : WebMvcProperties

Spring Boot 允许通过 application.properties 去定义一下配置，配置外部化

WebMvcProperties 配置前缀: spring.mvc	eg. spring.mvc.servlet
```



### 异常处理

#### 传统的 Servlet web.xml 错误页面

- 优点：统一处理，业界标准
- 不足：灵活度不够，只能定义web.xml文件里面

<error-page> 处理逻辑：

- 处理状态码 <error-code>
- 处理异常类型 <exception-type>
- 处理服务 <location>



#### Spring Web MVC 异常处理

- @ExceptionHandler
  - 优点：易于理解，尤其是全局异常处理
  - 不足：很难完全掌握所有的异常类型
- @RestControllerAdvice = @ControllerAdvice + @ResponseBody
- @ControllerAdvice专门拦截（AOP）@Controller



#### Spring Boot 错误处理页面

- 实现 ErrorPageRegistrar 接口
  - 状态码：比较通用，不需要理解Spring Web MVC异常体系
  - 不足：页面处理的路径必须固定
- 注册 ErrorPage 对象
- 实现 ErrorPage  对象中的Path路径Web服务



### 视图技术

#### View

- render 方法 ：处理页面渲染的逻辑，例如：Velocity、JSP、Thymeleaf

#### ViewResolver

- View Resolver = 页面 + 解析器 -> resolveViewName；寻找合适/对应View对象
- RequestURI -> RequestMappingHandlerMapping -> HandlerMethod -> return "viewName" -> 完整的页面名称 = prefix + "viewName" + suffix ->ViewResolver -> View -> render -> HTML
- SpringBoot 解析完整的页面路径：
  - spring.mvc.view.prefix + HandlerMethod return + spring.mvc.view.suffix

##### ContentNegotiatingViewResolver 

- 用于处理多个ViewResolver:JSP、Velocity、Thymeleaf
- 当所有的ViewResover配置完成时，他们的order默认值一样，所以先来先服务(List)
- 当他们定义自己的order，通过order来倒序排列

#### Thymeleaf

##### 自动装配类：ThymeleafAutoConfiguration

配置项前缀：spring.thymeleaf

模板寻找前缀：spring.thymeleaf.prefix

模板寻找后缀：spring.thymeleaf.suffix



代码示例：/thymeleaf/index.htm

prefix: /thymeleaf/

return value: index

suffix: .htm



### 国际化（i18n）

##### 自动装配类： MessageSourceAutoConfiguration

- Locale/LocaleContext
- LocaleContextHolder
- LocaleResolver/LocaleContextResolver
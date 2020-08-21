package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import  org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import  springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import  springfox.documentation.swagger2.annotations.EnableSwagger2;

//swagger2的配置文件，在项目的启动类的同级文件建立
@ComponentScan("com.example.demo")
@Configuration
@EnableSwagger2
//是否开启swagger
//@ConditionalOnProperty(name="swagger.enable",havingValue = "true")
public class Swagger2 implements WebMvcConfigurer {
//public class Swagger2 extends WebMvcConfigurationSupport {
    //swagger2的配置文件,这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.example.demo")).paths(PathSelectors.any())
                .build();
    }

    //构建api文档的详细信息函数，注意这里的注解引用的是哪个
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 测试使用 Swagger2")
                //版本号
                .version("1.0")
                .build();
    }

    /**
     * 解决swagger-ui.html 404无法访问的问题
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

//    private static final Logger logger= LoggerFactory.getLogger(Swagger2.class);
//    @Autowired()
//    private AuthenticationInterceptor authenticationInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        logger.info("拦截器SSS");
//        // 拦截所有请求，通过判断是否有 @LoginToken注解 决定是否需要登录
////        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/**")
//                .excludePathPatterns("/hello/login")
//        .excludePathPatterns("/hello/swagger-ui.html");
//
//    }

}

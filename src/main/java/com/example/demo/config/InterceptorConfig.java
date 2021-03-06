package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * .
 *
 * @author Mei Ruoxiao
 * @since 2020/8/20
 */

//  注册拦截器到SpringMvc
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private static final Logger logger= LoggerFactory.getLogger(InterceptorConfig.class);
    @Resource
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("拦截器SSS");
        // 拦截所有请求，通过判断是否有 @LoginToken注解 决定是否需要登录
        registry.addInterceptor(interceptor).addPathPatterns("/**")
                .excludePathPatterns("/**/user/login","/error")
                .excludePathPatterns("/**/swagger-ui.html",
                        "/**/webjars/springfox-swagger-ui/**",
                        "/**/swagger-resources/**");
//               "/**/webjars/springfox-swagger-ui/css/typography.css",
//                "/**/webjars/springfox-swagger-ui/images/favicon-32x32.png",
//                "/**/swagger-resources"
//                "/**/swagger-resources/configuration/ui",
//                "/**/null/swagger-resources/configuration/ui",
        // 拦截所有请求，通过判断是否有 @LoginToken注解 决定是否需要登录
//        registry.addInterceptor(authenticationInterceptor()).addPathPatterns("/**");


    }

//    @Bean
//    public AuthenticationInterceptor authenticationInterceptor() {
//        return new AuthenticationInterceptor();
//    }

}

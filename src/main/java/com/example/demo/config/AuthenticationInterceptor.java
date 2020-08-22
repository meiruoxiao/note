package com.example.demo.config;

import com.example.demo.annotation.LoginToken;
import com.example.demo.annotation.PassToken;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * .
 *
 * @author Mei Ruoxiao
 * @since 2020/8/20
 */
//配置拦截器
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("++++++++++++++++++");
        System.out.println(request.getServletPath());//打印具体请求地址
        System.out.println("+++++++++++++++++++++");

        // 从 http 请求头中取出 token
        String token = request.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //检查是否有PassToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {

            PassToken loginToken = method.getAnnotation(PassToken.class);
            if (loginToken.required()) {
                return true;
            }
        }else if (token == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return true;
        }else{



        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(LoginToken.class)) {
//            LoginToken checkToken = method.getAnnotation(LoginToken.class);
//            if (checkToken.required()) {
                // 执行认证
//                if (token == null) {
//                    response.sendRedirect(request.getContextPath() + "/user/login");
//                    return true;
//                }
                // 获取 token 中的 userId
            long userId = JWTUtil.getUserId(token);
            User user = userService.getById(userId);
            if (user == null) {
                throw new RuntimeException("用户不存在，请重新登录");
            }
            Boolean verify = JWTUtil.verify(token);
            if (!verify) {
                throw new RuntimeException("非法访问！");
            }
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

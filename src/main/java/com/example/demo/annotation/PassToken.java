package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * .
 *
 * @author Mei Ruoxiao
 * @since 2020/8/20
 */
@Target({ElementType.METHOD, ElementType.TYPE})//可以给方法进行注解,可以给一个类型进行注解，比如类、接口、枚举
@Retention(RetentionPolicy.RUNTIME)//可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
@Documented//注解表示将注解信息写入到 javadoc 文档中。
public @interface PassToken {
    boolean required() default true;
}

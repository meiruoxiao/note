package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * .
 *
 * @author Mei Ruoxiao
 * @since 2020/8/20
 */

/**
 * @Target 注解限定了该注解的使用场景
 * ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
 * ElementType.CONSTRUCTOR 可以给构造方法进行注解
 * ElementType.FIELD 可以给属性进行注解
 * ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
 * ElementType.METHOD 可以给方法进行注解
 * ElementType.PACKAGE 可以给一个包进行注解
 * ElementType.PARAMETER 可以给一个方法内的参数进行注解
 * ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举
*****************************************************************
 * @Retention 注解用来标记这个注解的留存时间
 * RetentionPolicy.SOURCE。注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
 * RetentionPolicy.CLASS。注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
 * RetentionPolicy.RUNTIME。注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
***********************************************************
 * @Documented 注解表示将注解信息写入到 javadoc 文档中。
 * 在默认情况下，我们的注解信息是不会写入到 Javadoc 文档中的。但如果该注解有 @Documented 标识，那么该注解信息则会写入到 javadoc 文档中。
 */
@Target({ElementType.METHOD, ElementType.TYPE})//可以给方法进行注解,可以给一个类型进行注解，比如类、接口、枚举
@Retention(RetentionPolicy.RUNTIME)//可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。
@Documented//注解表示将注解信息写入到 javadoc 文档中。
/**
 * 在Java 中只有四个元注解，它们分别是：@Target、@Retention、@Documented、@Inherited。     元注解
 */

//注解体指定了注解的名字
public @interface LoginToken {

    //注解属性
    boolean required() default true;
}
//用法@LoginToken(required = true)
package com.example.demo.utils;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
public class R<T> extends HashMap<String,Object> {
    private static final long serialVersionUID = 1L;
    private T data;

    public R(){
        put("code",HttpStatus.SC_OK);
        put("msg","success");
    }
    /*
    <!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpcore -->
		<!--消息头，消息体http-->
		<dependency>
			<groupId>org.apache.httpcomponents</groupId>
			<artifactId>httpcore</artifactId>
			<version>4.4.12</version>
		</dependency>
     */
    public static R error(){return error(HttpStatus.SC_INTERNAL_SERVER_ERROR,"未知异常，请联系管理员");}

    public static R error(String msg) {return error(HttpStatus.SC_INTERNAL_SERVER_ERROR,msg);}

    public static R error(int code, String msg) {
        R r=new R();
        r.put("code",code);
        r.put("msg",msg);
        return r;
    }

    public static R ok(){
        return new R();
    }

    public static R ok(String msg){
        R r=new R();
        r.put("msg",msg);
        return r;
    }

    public static R ok(Map<String, Object> map){
        R r=new R();
        r.putAll(map);
        return r;
    }

    public R put(String key,Object value){
        super.put(key,value);
        return this;
    }

    public Integer getCode(){
        return (Integer) this.get("code");
    }

    public <T> T putAll(T data){
        super.put("data",data);
        return (T) this;
    }


//    public <T> T R(T data) {
//        return data;
//    }

}





























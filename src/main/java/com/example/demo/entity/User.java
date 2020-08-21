package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data//自动创建setget方法
@TableName(value = "user")//这里的表名是数据库的对应表名
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId(value = "userid",type = IdType.AUTO)
	private Long id;
	@TableField(value = "name")//这里对应的是数据库的字段名
	private String name;
	@TableField(value = "password")
	private String pwd;

}

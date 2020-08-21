package com.example.demo.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
	List<User> findAll();
//	User findById(Long id);
//	void insert(User user);
//	void update(User user);
	//void delete(Long id);
}

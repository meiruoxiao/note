package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;

    public User selectOne(User user){
        QueryWrapper<User> queryWrapper= Wrappers.query(user);
        //-------------------------------------------------------------
        //        QueryWrapper<User> queryWrapper= Wrappers.query(user1);
        //		||			||			||			||			||
        //		QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
        //		queryWrapper.eq(user1!=null,"name",userName);
        //-------------------------------------------------------------
        return userMapper.selectOne(queryWrapper);
    }
    @Cacheable(key = "UserAll")
    public List selectAll() {
//        List<User> list=userMapper.findAll();
        QueryWrapper<User> queryWrapper=Wrappers.query();
        List<User> list=userMapper.selectList(queryWrapper);
        return list;
    }
}

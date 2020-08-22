package com.example.demo.controller;

import com.example.demo.annotation.LoginToken;
import com.example.demo.annotation.PassToken;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JWTUtil;
import com.example.demo.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/")
@Api(value = "/hello", description = "User相关api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/user", method = RequestMethod.GET)
//    @LoginToken
    public Object index() {
        User user = new User();
        user.setName("Adam");
        user.setPwd("123456");
        userService.save(user);
//        ModelAndView modelAndView = new ModelAndView("/index");
//        modelAndView.addObject("count", userMapper.findAll().size());
        return R.ok().putAll(user);

    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录", notes = "根据用户名和密码登录，返回token")
//    @PassToken
    public R login(HttpServletRequest request,
                   @RequestParam(value = "name") String userName,
                   @RequestParam(value = "pwd") String passWord) {
        logger.info("----------------------------");
        String msg = "用户不存在";
        User user1 = new User();
        user1.setName(userName);
        User user = userService.selectOne(user1);
        if (user != null) {
            if (user.getPwd().equals(passWord)) {
//				request.getSession().setAttribute("user",user);
                msg = "登录成功";
                String token = JWTUtil.generateToken(user, 1);
                return R.ok(msg).put("user", user).put("Authorization", token);

            } else {
                msg = "密码错误";
                return R.ok(msg);
            }
        }
        return R.error(msg);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    @ApiOperation(value = "查询所有用户")
//    @LoginToken
    public Object all() {
        List<Map<String, Object>> list;
//		list=userService.listMaps();
        list = userService.selectAll();
        return R.ok().putAll(list);
    }
}

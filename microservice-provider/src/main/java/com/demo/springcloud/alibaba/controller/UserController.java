package com.demo.springcloud.alibaba.controller;


import com.demo.springcloud.alibaba.entity.User;
import com.demo.springcloud.alibaba.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/queryById")
    public Object queryUserInfo(@RequestParam(value = "id") String id) {
        if (StringUtils.isEmpty(id)) {
            return "param is empty";
        }

        User user = userService.getUserInfo(Integer.valueOf(id));
        if (user == null) {
            return "record is not exist";
        }
        return user;
    }
}

package com.demo.springcloud.alibaba.dao;

import com.demo.springcloud.alibaba.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao {

    private static Map<String, User> userList = new HashMap<>();

    static {

        User zs = new User(1, "张三", "深圳市");
        User ls = new User(2, "李四", "湖南市");
        User ww = new User(3, "王五", "武汉市");
    }

}

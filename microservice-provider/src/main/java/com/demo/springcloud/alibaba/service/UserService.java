package com.demo.springcloud.alibaba.service;

import com.demo.springcloud.alibaba.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private static Map<Integer, User> userMap = new HashMap<>();

    static {

        User zs = new User(1, "张三", "深圳市");
        User ls = new User(2, "李四", "湖南市");
        User ww = new User(3, "王五", "武汉市");

        userMap.put(zs.getId(), zs);
        userMap.put(ls.getId(), ls);
        userMap.put(ww.getId(), ww);
    }


    public User getUserInfo(Integer id) {
        return userMap.get(id);
    }

}

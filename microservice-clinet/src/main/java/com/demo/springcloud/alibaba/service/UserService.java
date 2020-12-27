package com.demo.springcloud.alibaba.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value ="provider-application")
public interface UserService {

    @GetMapping("/user/queryById")
    String queryUserInfo(@RequestParam(value = "id")String id);
}

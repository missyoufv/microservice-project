package com.demo.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {


    @Value("${user.name}")
    private String name;

    @Value("${user.age}")
    private String age;

    @GetMapping(value = "configInfo")
    public String configInfo() {
        return "config name : "+ name + " age: " +age;
    }
}

package com.demo.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {


    @Value("${config.info}")
    private String message;

    @Value("${common.info:''}")
    private String commonInfo;

    @GetMapping(value = "configInfo")
    public String configInfo() {
        String result = " get data from nacos config center, " + message;
        return result;
    }


    @GetMapping(value = "commonConfigInfo")
    public String commonConfigInfo() {
        String result = " get data from common nacos config, " + commonInfo;
        return result;
    }
}

package com.demo.springcloud.alibaba.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class EchoController {

    @Value("${server.port}")
    private String port;

    @Value("${spring.cloud.nacos.discovery.metadata.version}")
    private String version;

    @GetMapping(value = "echo/{name}")
    public String echo(@PathVariable String name) {
        String message = " Hello Nacos Discovery ".concat(name).concat(" api version is :").concat(version);
        log.info(message);
        return message;
    }
}

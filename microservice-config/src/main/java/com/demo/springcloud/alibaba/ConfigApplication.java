package com.demo.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext =  SpringApplication.run(ConfigApplication.class);
        String userName = applicationContext.getEnvironment().getProperty("user.name");
        String userAge = applicationContext.getEnvironment().getProperty("user.age");
        String currentEnv = applicationContext.getEnvironment().getProperty("current.env");
        System.err.println("in "+currentEnv+" enviroment; "+"user name :" + userName + "; age: " + userAge);
    }
}

package com.demo.springcloud.alibaba.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.demo.springcloud.alibaba.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/test")
@Slf4j
@Data
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private UserService userService;

    @NacosInjected
    private NamingService namingService;

    AtomicInteger count = new AtomicInteger(0);


    @GetMapping(value = "/discover")
    @ResponseBody
    public List<Instance> get(@RequestParam String serviceName) throws NacosException {
        return namingService.getAllInstances(serviceName);
    }

    @GetMapping("/direct")
    public String direct(String name) {

        String url = "http://localhost:9200/echo/"+name;
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @GetMapping("/myLoadBalance")
    public String myLoadBalance(String name) {
        if (StringUtils.isEmpty(name)) {
            return "param is empty";
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("provider-application");
        if (!CollectionUtils.isEmpty(instances)) {
            int index = count.getAndIncrement();
            if (index  < Integer.MAX_VALUE) {
                index = count.get() % instances.size();
            }else {
                index = 0;
                count.set(0);
            }
            String url = String.format("http://%s:%s/echo/%s",instances.get(index).getHost(),instances.get(index).getPort(),name);
            log.info("invoke url is:{}",url);
            String result = restTemplate.getForObject(url, String.class);
            return result;
        }else {
            return "invoker fail ! service provider not exist";
        }
    }

    @GetMapping("/loadBalance")
    public String loadBalance(String name) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("provider-application");
        String url = String.format("http://%s:%s/echo/%s",serviceInstance.getHost(),serviceInstance.getPort(),name);
        log.info("invoke url is:{}",url);
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }



    @GetMapping("/restAndLoadBal")
    public String restAndLoadBal(String name) {
        String url = String.format("http://%s/echo/%s","provider-application",name);
        log.info("invoke url is:{}",url);
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @ResponseBody
    @GetMapping("feignInvoke")
    public Object feignInvoke(@RequestParam(name = "id" ) String id) {
        return userService.queryUserInfo(id);
    }

}

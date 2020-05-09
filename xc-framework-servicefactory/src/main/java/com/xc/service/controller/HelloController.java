package com.xc.service.controller;

import com.xc.service.InitServiceFactory.AppService;
import com.xc.service.InitServiceFactory.SFactory;
import com.xc.service.InitServiceFactory.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private SFactory sFactory;

    @RequestMapping("/ye")
    public Map<String, Object> hello(String serviceName) {
        ServiceType serviceType = ServiceType.valueOf(serviceName.toUpperCase());

        AppService appService = sFactory.getMap().get(serviceType);
        Map<String, Object> stringObjectMap = appService.processService(new HashMap<>());
        System.out.println(sFactory.getMap());
        return stringObjectMap;
    }

}

package com.xc.service.myservice;

import com.xc.service.InitServiceFactory.AppService;
import com.xc.service.InitServiceFactory.MyService;
import com.xc.service.InitServiceFactory.ServiceType;
import org.springframework.stereotype.Service;

import java.util.Map;

@MyService(serviceType = ServiceType.CREATE_ORDER)
@Service
public class HelloService implements AppService {
    @Override
    public Map<String, Object> processService(Map<String, Object> params) {
        System.out.println("*******hello service******");
        return null;
    }
}

package com.xc.service.InitServiceFactory;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SFactory {
    private Map<ServiceType, AppService> map = new ConcurrentHashMap<>();

    public Map<ServiceType, AppService> getMap() {
        return map;
    }

    public void setMap(HashMap<ServiceType, AppService> map) {
        this.map = map;
    }
}

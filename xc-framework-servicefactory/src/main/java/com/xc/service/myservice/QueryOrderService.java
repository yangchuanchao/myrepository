package com.xc.service.myservice;

import com.xc.service.InitServiceFactory.AppService;
import com.xc.service.InitServiceFactory.MyService;
import com.xc.service.InitServiceFactory.ServiceType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@MyService(serviceType = ServiceType.QUERY_ORDER)
@Service
public class QueryOrderService implements AppService {
    @Override
    public Map<String, Object> processService(Map<String, Object> params) {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("id", UUID.randomUUID().toString().replace("-", ""));
        stringObjectHashMap.put("goodsName", "iphone 11 Pro Max");
        stringObjectHashMap.put("price", 10999);
        return stringObjectHashMap;
    }
}

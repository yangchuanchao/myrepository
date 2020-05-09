package com.xc.manage.course.clients;

import com.xuecheng.framework.client.XcServiceList;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = XcServiceList.XC_SERVICE_MANAGE_CMS)
public interface CourseClient {

}

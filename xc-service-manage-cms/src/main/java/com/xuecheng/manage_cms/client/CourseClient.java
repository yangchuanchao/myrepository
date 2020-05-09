package com.xuecheng.manage_cms.client;

import com.xuecheng.framework.client.XcServiceList;
import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.manage_cms.config.MyFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = XcServiceList.XC_SERVICE_MANAGE_COURSE, fallbackFactory = MyFallbackFactory.class)
public interface CourseClient {
    @RequestMapping("/course/get")
    public CourseBase getCourse();
}

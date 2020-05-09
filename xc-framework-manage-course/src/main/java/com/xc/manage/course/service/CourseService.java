package com.xc.manage.course.service;

import com.xuecheng.framework.client.XcServiceList;
import com.xuecheng.framework.domain.course.CourseBase;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = XcServiceList.XC_SERVICE_MANAGE_CMS)
public interface CourseService {
    @PostMapping("/course/findCourse/{id}")
    public List<CourseBase> findCourse(@PathVariable String id);
}

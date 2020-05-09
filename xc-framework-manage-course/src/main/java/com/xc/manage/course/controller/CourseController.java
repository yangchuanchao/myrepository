package com.xc.manage.course.controller;

import com.xuecheng.framework.domain.course.CourseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {

    @RequestMapping("/get")
    public CourseBase getCourse() {
        CourseBase courseBase = new CourseBase();
        courseBase.setCompanyId(UUID.randomUUID().toString());
        courseBase.setDescription("测试一下");
        courseBase.setId(UUID.randomUUID().toString());
        courseBase.setCompanyId(UUID.randomUUID().toString());
        return courseBase;
    }
}

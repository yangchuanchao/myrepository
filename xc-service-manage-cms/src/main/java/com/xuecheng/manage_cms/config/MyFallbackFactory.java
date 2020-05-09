package com.xuecheng.manage_cms.config;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.manage_cms.client.CourseClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class MyFallbackFactory implements FallbackFactory<CourseClient> {
    @Override
    public CourseClient create(Throwable throwable) {
        return new CourseClient() {

            @Override
            public CourseBase getCourse() {
                return new CourseBase();
            }
        };
    }
}

package com.xc.service.InitServiceFactory;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InitServiceFactory implements CommandLineRunner {
    @Autowired
    private ConfigurableListableBeanFactory beanFactory;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private SFactory sFactory;

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(MyService.class);
        System.out.println(beansWithAnnotation);
        beansWithAnnotation.forEach((a, b) -> {
            if (b != null) {
                Class<?> aClass = b.getClass();
                MyService annotation = aClass.getAnnotation(MyService.class);
//                System.out.println(annotation.serviceType());
                AppService bean = beanFactory.getBean(a, AppService.class);
                ServiceType serviceType = annotation.serviceType();
                System.out.println(serviceType);
                HashMap<ServiceType, AppService> map = new HashMap<>();
                sFactory.getMap().put(serviceType, bean);

            }
        });
    }


}

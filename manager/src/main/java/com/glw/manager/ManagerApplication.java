package com.glw.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author : glw
 * @date : 2019/6/27
 * @time : 11:31
 * @Description: 产品启动类
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.glw.entity"})
public class ManagerApplication extends WebMvcConfigurationSupport {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

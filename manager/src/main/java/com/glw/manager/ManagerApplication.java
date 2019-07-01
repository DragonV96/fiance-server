package com.glw.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author : glw
 * @date : 2019/6/27
 * @time : 11:31
 * @Description: 产品启动类
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.glw.entity"})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class);
    }
}

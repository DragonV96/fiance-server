package com.glw.seller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/**
 * @author : glw
 * @date : 2019/7/8
 * @time : 17:04
 * @Description :
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class SellerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SellerApplication.class);
    }
}

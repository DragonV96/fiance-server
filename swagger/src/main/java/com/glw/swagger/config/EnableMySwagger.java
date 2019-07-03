package com.glw.swagger.config;

import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.*;

/**
 * @author : glw
 * @date : 2019/7/3
 * @time : 15:11
 * @Description : 开启swagger文档自动生成功能
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(Swagger2Config.class)
@EnableSwagger2
public @interface EnableMySwagger {
}

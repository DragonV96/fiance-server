package com.glw.manager.error;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author : glw
 * @date : 2019/7/1
 * @time : 13:59
 * @Description : 错误处理相关配置
 */
@Configuration
public class ErrorConfiguration {
    @Bean
    public MyErrorController basicErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties,
                                                  ObjectProvider<List<ErrorViewResolver>> errorViewResolvers) {
        return new MyErrorController(errorAttributes, serverProperties.getError(),
                errorViewResolvers.getIfAvailable());
    }
}

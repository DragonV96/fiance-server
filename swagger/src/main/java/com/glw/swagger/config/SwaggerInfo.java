package com.glw.swagger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : glw
 * @date : 2019/7/3
 * @time : 15:02
 * @Description : swagger信息实体类
 */
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerInfo {
    private String groupName = "controller";

    private String basePackage;

    private String antPah;

    private String title = "HTTP API";

    private String description = "Swagger自动生成接口文档";

    private String license = "Apache License Version 2.0";

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getAntPah() {
        return antPah;
    }

    public void setAntPah(String antPah) {
        this.antPah = antPah;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
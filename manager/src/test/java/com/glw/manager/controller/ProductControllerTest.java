package com.glw.manager.controller;

import com.glw.entity.Product;
import com.glw.entity.enums.ProductStatus;
import com.glw.util.RestUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : glw
 * @date : 2019/7/2
 * @time : 17:11
 * @Description : 测试ProductController类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    private static RestTemplate rest = new RestTemplate();

    @Value("http://localhost:$(local.server.port)/manager")
    private String baseUrl;

    // 正常产品数据
    private static List<Product> normals = new ArrayList<>();

    // 测试前执行一次，类似于构造方法
    @BeforeClass
    public static void init () {
        Product p1 = new Product("002", "产品123", ProductStatus.AUDITING.name(), BigDecimal.valueOf(10),
                BigDecimal.valueOf(1), BigDecimal.valueOf(3.33));
        Product p2 = new Product("003", "xxx", ProductStatus.AUDITING.name(), BigDecimal.valueOf(20),
                BigDecimal.valueOf(0), BigDecimal.valueOf(3.88));
        Product p3 = new Product("004", "bbb", ProductStatus.AUDITING.name(), BigDecimal.valueOf(100),
                BigDecimal.valueOf(20), BigDecimal.valueOf(3.99));
        normals.add(p1);
        normals.add(p2);
        normals.add(p3);
    }

    @Test
    public void create () {
        normals.forEach(product -> {
            Product result = RestUtil.postJSON(rest, baseUrl + "/products", product, Product.class);
            Assert.notNull(result.getCreateAt(), "插入失败");
        });
    }
}

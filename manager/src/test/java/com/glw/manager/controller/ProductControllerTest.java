package com.glw.manager.controller;

import com.glw.entity.Product;
import com.glw.entity.enums.ProductStatus;
import com.glw.util.RestUtil;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : glw
 * @date : 2019/7/2
 * @time : 17:11
 * @Description : 自动化测试ProductController类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)   // 按照名称字典顺序执行单元测试
public class ProductControllerTest {

    private static RestTemplate rest = new RestTemplate();

    @Value("http://localhost:${local.server.port}/manager")
    private String baseUrl;

    // 正常产品数据
    private static List<Product> normals = new ArrayList<>();

    // 异常产品数据
    private static List<Product> exceptions = new ArrayList<>();

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


        Product e1 = new Product(null, "编号不可为空", ProductStatus.AUDITING.name(), BigDecimal.valueOf(10),
                BigDecimal.valueOf(1), BigDecimal.valueOf(3.33));
        Product e2 = new Product("E003", "收益率范围错误", ProductStatus.AUDITING.name(), BigDecimal.ZERO,
                BigDecimal.valueOf(0), BigDecimal.valueOf(42));
        Product e3 = new Product("E004", "投资步长需为整数", ProductStatus.AUDITING.name(), BigDecimal.ZERO,
                BigDecimal.valueOf(1.023), BigDecimal.valueOf(3.99));
        normals.add(e1);
        normals.add(e2);
        normals.add(e3);

        ResponseErrorHandler errorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

            }
        };
        rest.setErrorHandler(errorHandler);
    }

    /**
     * 测试插入正常产品数据
     */
    @Test
    @Transactional
    public void create () {
        normals.forEach(product -> {
            Product result = RestUtil.postJSON(rest, baseUrl + "/products", product, Product.class);
            Assert.notNull(result.getCreateAt(), "插入失败");
        });
    }

    /**
     * 测试插入异常产品数据
     */
    @Test
    public void createException () {
        normals.forEach(product -> {
            Map<String, String> result = RestUtil.postJSON(rest, baseUrl + "/products", product, HashMap.class);
            Assert.isTrue(result.get("message").equals(product.getName()), "插入成功");
        });
    }

    @Test
    public void findOne() {
        normals.forEach(product -> {
            Product result = rest.getForObject(baseUrl + "/products/" + product.getId(), Product.class);
            Assert.isTrue(result.getId().equals(product.getId()), "查询失败");

        });

        normals.forEach(product -> {
            Product result = rest.getForObject(baseUrl + "/products/" + product.getId(), Product.class);
            Assert.isNull(result, "查询失败");
        });
    }
}

package com.glw.manager.controller;

import com.glw.entity.Product;
import com.glw.manager.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : glw
 * @date : 2019/6/27
 * @time : 14:41
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private static Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        LOG.info("创建产品，参数：{}", product);
        Product result = productService.addProduct(product);

        LOG.info("创建产品，结果：{}", result);
        return result;
    }
}

package com.glw.seller.controller;

import com.glw.entity.Product;
import com.glw.seller.service.ProductRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : glw
 * @date : 2019/7/25
 * @time : 15:19
 * @Description : 产品相关
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRpcService productRpcService;

    @RequestMapping("/{id}")
    public Product findOne(@PathVariable String id) {
        return productRpcService.findOne(id);
    }

}

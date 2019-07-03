package com.glw.manager.controller;

import com.glw.entity.Product;
import com.glw.manager.service.ProductService;
import com.glw.swagger.config.EnableMySwagger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author : glw
 * @date : 2019/6/27
 * @time : 14:41
 */
@RestController
@RequestMapping("/products")
@EnableMySwagger
@Api(tags = "产品管理", description = "产品增删改查控制层")
public class ProductController {

    private static Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @ApiOperation(value = "创建产品", notes = "根据对应业务规则添加相应的产品")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        LOG.info("创建产品，参数：{}", product);
        Product result = productService.addProduct(product);

        LOG.info("创建产品，结果：{}", result);
        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product findOne(@PathVariable String id) {
        LOG.info("查询单个产品，id={}", id);
        Product product = productService.findOne(id);
        LOG.info("查询单个产品，结果={}", product);
        return product;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<Product> query(String ids, BigDecimal minRewardRate, BigDecimal maxRewardRate, String status, @RequestParam(defaultValue = "0") int pageNum, @RequestParam(defaultValue = "10") int pageSize) {
        LOG.info("查询产品，ids={}, minRewardRate={}, maxRewardRate={}, status={}, pageNum={}, pageSize={}", ids, minRewardRate, maxRewardRate, status, pageNum, pageSize);
        List<String> idList = null, statusList = null;
        if (!StringUtils.isEmpty(ids)) {
            idList = Arrays.asList(ids.split(","));
        }
        if (!StringUtils.isEmpty(status)) {
            statusList = Arrays.asList(status.split(","));
        }

        Pageable pageable = new PageRequest(pageNum, pageSize);
        Page<Product> page = productService.query(idList, minRewardRate, maxRewardRate, statusList, pageable);
        LOG.info("查询产品，结果={}", page);
        return page;
    }
}

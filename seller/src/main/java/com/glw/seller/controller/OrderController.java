package com.glw.seller.controller;

import com.glw.entity.Order;
import com.glw.seller.service.OrderService;
import com.glw.swagger.config.EnableMySwagger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : glw
 * @date : 2019/7/26
 * @time : 17:44
 * @Description : 订单控制层
 */
@RestController
@RequestMapping("/order")
@EnableMySwagger
@Api(tags = "订单管理", description = "订单增删改查控制层")
public class OrderController {
    static Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 下单
     * @param order
     * @return
     */
    @ApiOperation(value = "创建订单", notes = "创建订单")
    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public Order apply(@RequestHeader String authId, @RequestHeader String sign, @RequestBody Order order) {
        LOG.info("申购请求:{}", order);
        order = orderService.apply(order);
        LOG.info("申购结果:{}", order);
        return order;
    }
}

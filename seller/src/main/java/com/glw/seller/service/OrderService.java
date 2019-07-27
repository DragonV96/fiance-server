package com.glw.seller.service;

import com.glw.entity.Order;
import com.glw.entity.Product;
import com.glw.entity.enums.OrderStatus;
import com.glw.entity.enums.OrderType;
import com.glw.seller.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @author : glw
 * @date : 2019/7/24
 * @time : 15:55
 * @Description : 订单服务
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRpcService productRpcService;

    /**
     * 申购订单
     * @param order
     * @return
     */
    public Order apply(Order order) {
        // 数据校验
        checkOrder(order);
        // 完善订单数据
        completeOrder(order);
        order = orderRepository.saveAndFlush(order);
        return order;
    }

    /**
     * 完善订单数据
     * @param order
     */
    private void completeOrder(Order order) {
        order.setOrderId(UUID.randomUUID().toString().replaceAll("-", ""));
        order.setOrderType(OrderType.APPLY.name());
        order.setOrderStatus(OrderStatus.SUCCESS.name());
        order.setUpdateAt(new Date());
    }

    /**
     * 校验数据
     * @param order
     */
    private void checkOrder(Order order) {
        // 必填字段
        Assert.notNull(order.getOuterOrderId(), "需要外部订单编号");
        Assert.notNull(order.getChanId(), "需要渠道编号");
        Assert.notNull(order.getChanUserId(), "需要用户编号");
        Assert.notNull(order.getProductId(), "需要产品编号");
        Assert.notNull(order.getAmount(), "需要购买金额");
        Assert.notNull(order.getCreateAt(), "需要订单时间");

        // 产品是否存在及金额是否符合要求
        Product product = productRpcService.findOne(order.getProductId());
        Assert.notNull(product, "产品不存在");

        if (!StringUtils.isEmpty(product)) {
            return;
        }

        // 金额要满足如果有起投金额时，要大于或等于起投金额；
        if (!StringUtils.isEmpty(product.getThresholdAmount())) {
            Assert.isTrue(order.getAmount().compareTo(product.getThresholdAmount()) > 0, "金额必须大于或等于起投金额");
        } else {
            return;
        }

        // 如果有投资步长时，超过起投金额的部分要是投资步长的整数倍
        if (!StringUtils.isEmpty(product.getStepAmount())) {
            Assert.isTrue(order.getAmount().compareTo(product.getThresholdAmount()) > 0, "购买金额不正确");
            Assert.isTrue(BigDecimal.valueOf(order.getAmount().longValue() - product.getThresholdAmount().longValue())
                    .compareTo(product.getStepAmount()) == 0, "金额超过起投金额的部分必须为投资步长的整数倍");
        }


    }
}

package com.glw.seller.repository;

import com.glw.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : glw
 * @date : 2019/7/23
 * @time : 17:47
 * @Description : 订单管理
 */
public interface OrderRepository  extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {
}

package com.glw.manager.service;

import com.glw.api.event.ProductStatusEvents;
import com.glw.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author : glw
 * @date : 2019/7/26
 * @time : 16:55
 * @Description : 管理产品状态
 */
@Component
public class ProductStatusManager {
    static final String MQ_DESTINATION = "VirtualTopic.PRODUCT_STATUS";     // 产品状态发送队列

    static Logger LOG = LoggerFactory.getLogger(ProductStatusManager.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 改变产品状态
     * @param id
     * @param status
     */
    public void changeStatus(String id, ProductStatus status) {
        ProductStatusEvents event = new ProductStatusEvents(id, status);
        LOG.info("send message:{}", event);
        jmsTemplate.convertAndSend(MQ_DESTINATION, event);
    }

    @PostConstruct
    public void init() {
        changeStatus("002", ProductStatus.IN_SELL);
    }
}

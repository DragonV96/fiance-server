package com.glw.seller.service;

import com.glw.api.ProductRpc;
import com.glw.api.event.ProductStatusEvents;
import com.glw.entity.Product;
import com.glw.entity.enums.ProductStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : glw
 * @date : 2019/7/8
 * @time : 17:43
 * @Description : 产品相关服务
 */
@Service
public class ProductRpcService implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger LOG = LoggerFactory.getLogger(ProductRpcService.class);

    static final String MQ_DESTINATION = "Consumer.cache.VirtualTopic.PRODUCT_STATUS";     // 产品状态消费队列

    @Autowired
    private ProductRpc productRpc;

    @Autowired
    private ProductCache productCache;

    /**
     * 查询全部产品
     * @return
     */
    public List<Product> findAll() {
        return productCache.readAllCache();
    }

    /**
     * 查询单个产品
     * @return
     */
    public Product findOne(String id) {
        Product product = productCache.readCache(id);
        if (product == null) {
            productCache.removeCache(id);
        }
        return productCache.readCache(id);
    }

//    @PostConstruct
    public void init() {
        findOne("002");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<Product> products = findAll();
        products.forEach(product -> {
            productCache.putCache(product);
        });
    }

    @JmsListener(destination = MQ_DESTINATION)
    void updateCache(ProductStatusEvents event) {
        LOG.info("receive event:{}", event);
        productCache.removeCache(event.getId());        // 首先清空缓存
        if (ProductStatus.IN_SELL.equals(event.getStatus())) {
            productCache.readCache(event.getId());
        }
    }
}

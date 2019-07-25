package com.glw.seller.service;

import com.glw.api.ProductRpc;
import com.glw.api.domain.ProductRpcReq;
import com.glw.entity.Product;
import com.glw.entity.enums.ProductStatus;
import com.hazelcast.core.HazelcastInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : glw
 * @date : 2019/7/25
 * @time : 15:29
 * @Description : 产品缓存
 */
@Component
public class ProductCache {
    static final String CACHE_NAME = "product";
    private static Logger LOG = LoggerFactory.getLogger(ProductCache.class);

    @Autowired
    private ProductRpc productRpc;

    @Autowired
    private HazelcastInstance hazelcastInstance;

    /**
     * 读取缓存
     * @param id
     * @return
     */
    @Cacheable(cacheNames = CACHE_NAME)
    public Product readCache(String id) {
        LOG.info("rpc查询单个产品，请求：{}", id);
        Product result = productRpc.findOne(id);
        LOG.info("rpc查询单个产品，结果：{}", result);
        return result;
    }

    /**
     * 添加缓存
     * @param product
     * @return
     */
    @CachePut(cacheNames = CACHE_NAME, key = "#product.id")
    public Product putCache(Product product) {
        return product;
    }

    /**
     * 清除缓存
     * @param id
     */
    @CacheEvict(cacheNames = CACHE_NAME)
    public void removeCache(String id) {

    }

    public List<Product> readAllCache() {
        Map map = hazelcastInstance.getMap(CACHE_NAME);
        List<Product> products = null;
        if (map.size() > 0) {
            products = new ArrayList<>();
            products.addAll(map.values());
        } else {
            products = findAll();
        }
        return products;
    }

    /**
     * 查询全部产品
     * @return
     */
    public List<Product> findAll() {
        ProductRpcReq req = new ProductRpcReq();
        List<String> status = new ArrayList<>();
        status.add(ProductStatus.IN_SELL.name());
        req.setStatusList(status);
        LOG.info("rpc查询全部产品，请求：{}", req);
        List<Product> result = productRpc.query(req);
        LOG.info("rpc查询全部产品，结果：{}", result);
        return result;
    }
}

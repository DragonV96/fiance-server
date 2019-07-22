package com.glw.manager.rpc;

import com.glw.api.ProductRpc;
import com.glw.api.domain.ProductRpcReq;
import com.glw.entity.Product;
import com.glw.manager.service.ProductService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : glw
 * @date : 2019/7/8
 * @time : 16:40
 * @Description : rpc服务实现类
 */
@AutoJsonRpcServiceImpl
@Service
public class ProductRpcImpl implements ProductRpc {

    private static Logger LOG = LoggerFactory.getLogger(ProductRpcImpl.class);

    @Autowired
    private ProductService productService;

    @Override
    public List<Product> query(ProductRpcReq req) {
        LOG.info("查询多个产品，请求：{}", req);
        Pageable pageable = new PageRequest(0, 1000, Sort.Direction.DESC, "rewardRate");
        Page<Product> result = productService.query(req.getIdList(), req.getMinRewardRate(), req.getMaxRewardRate(), req.getStatusList(), pageable);
        LOG.info("查询多个产品，结果：{}", result);
        return result.getContent();
    }

    @Override
    public Product findOne(String id) {
        LOG.info("查询多个产品，请求：{}", id);
        Product result = productService.findOne(id);
        LOG.info("查询多个产品，结果：{}", result);
        return null;
    }
}

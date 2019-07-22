package com.glw.api;

import com.glw.api.domain.ProductRpcReq;
import com.glw.entity.Product;
import com.googlecode.jsonrpc4j.JsonRpcService;

import java.util.List;

/**
 * @author : glw
 * @date : 2019/7/8
 * @time : 16:30
 * @Description : 产品相关的rpc服务
 */
@JsonRpcService("rpc/products")
public interface ProductRpc {

    /**
     * 查询多个产品
     * @param req
     * @return
     */
    List<Product> query(ProductRpcReq req);

    /**
     * 查询单个产品
     * @param id
     * @return
     */
    Product findOne(String id);
}

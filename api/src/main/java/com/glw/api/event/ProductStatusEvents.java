package com.glw.api.event;

import com.glw.entity.enums.ProductStatus;

import java.io.Serializable;

/**
 * @author : glw
 * @date : 2019/7/25
 * @time : 17:51
 * @Description : 产品状态事件
 */
public class ProductStatusEvents implements Serializable {

    private String id;
    private ProductStatus status;

    public ProductStatusEvents(String id, ProductStatus status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductStatusEvents{" +
                "id='" + id + '\'' +
                ", status=" + status +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}

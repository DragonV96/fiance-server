package com.glw.manager.repository;

import com.glw.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author : glw
 * @date : 2019/6/27
 * @time : 11:36
 * @Description: 产品sql接口
 */
public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {

}

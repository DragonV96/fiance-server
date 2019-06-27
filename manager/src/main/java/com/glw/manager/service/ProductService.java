package com.glw.manager.service;

import com.glw.entity.Product;
import com.glw.entity.enums.ProductStatus;
import com.glw.manager.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author : glw
 * @date : 2019/6/27
 * @time : 11:52
 * @Description: 产品服务类
 */
@Service
public class ProductService {

    private static Logger LOG = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {
        LOG.debug("创建产品，参数：{}", product);
        // 数据校验
        checkProduct(product);

        // 设置默认值
        setDefault(product);
        Product result = productRepository.save(product);

        LOG.debug("创建产品，结果：{}", result);
        return result;
    }

    /**
     * 设置默认值：创建时间、更新时间、投资步长、锁定期、状态
     * @param product
     */
    private void setDefault(Product product) {
        if (product.getCreateAt() == null) {
            product.setCreateAt(new Date());
        } else if (product.getUpdateAt() == null) {
            product.setUpdateAt(new Date());
        } else if (product.getStepAmount() == null) {
            product.setStepAmount(BigDecimal.ZERO);
        } else if (product.getUpdateAt() == null) {
            product.setUpdateAt(new Date());
        } else if (product.getLockTerm() == null) {
            product.setLockTerm(0);
        } else if (product.getStatus() == null) {
            product.setStatus(ProductStatus.AUDITING.name());
        }
    }

    /**
     * 产品数据校验：1，非空数据 2，收益率要再0-30以内 3，投资步长需为整数
     * @param product
     */
    private void checkProduct(Product product) {
        // 非空校验
        Assert.notNull(product.getId(), "产品编号不可为空");
        Assert.notNull(product.getName(), "产品名称不可为空");
        Assert.notNull(product.getThresholdAMount(), "起投金额不可为空");
        Assert.notNull(product.getStepAmount(), "投资步长不可为空");
        Assert.notNull(product.getLockTerm(), "锁定期不可为空");
        Assert.notNull(product.getRewardRate(), "收益率不可为空");
        Assert.notNull(product.getStatus(), "状态不可为空");

        // 收益率
        Assert.isTrue(BigDecimal.ZERO.compareTo(product.getRewardRate()) < 0 && BigDecimal.valueOf(30).compareTo(product.getRewardRate()) >= 0, "收益率范围错误");

        // 投资步长
        Assert.isTrue(BigDecimal.valueOf(product.getStepAmount().longValue()).compareTo(product.getStepAmount()) == 0, "投资步长需为整数");
    }

    public Product findOne(String id) {
        return null;
    }
}

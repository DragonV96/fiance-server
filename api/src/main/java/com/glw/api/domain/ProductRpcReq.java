package com.glw.api.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : glw
 * @date : 2019/7/8
 * @time : 16:34
 * @Description : 产品相关rpc请求对象
 */
public class ProductRpcReq {
    private List<String> idList;
    private BigDecimal minRewardRate;
    private BigDecimal maxRewardRate;
    private List<String> statusList;

    @Override
    public String toString() {
        return "ProductRpcReq{" +
                "idList=" + idList +
                ", minRewardRate=" + minRewardRate +
                ", maxRewardRate=" + maxRewardRate +
                ", statusList=" + statusList +
                '}';
    }

    public List<String> getIdList() {
        return idList;
    }

    public void setIdList(List<String> idList) {
        this.idList = idList;
    }

    public BigDecimal getMinRewardRate() {
        return minRewardRate;
    }

    public void setMinRewardRate(BigDecimal minRewardRate) {
        this.minRewardRate = minRewardRate;
    }

    public BigDecimal getMaxRewardRate() {
        return maxRewardRate;
    }

    public void setMaxRewardRate(BigDecimal maxRewardRate) {
        this.maxRewardRate = maxRewardRate;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }
}

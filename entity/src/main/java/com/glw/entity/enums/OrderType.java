package com.glw.entity.enums;

/**
 * @author : glw
 * @date : 2019/6/27
 * @time : 11:22
 * @Description: 产品类型
 */
public enum OrderType {
    APPLY("申购"),
    REDEEM("赎回");

    private String desc;

    OrderType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}

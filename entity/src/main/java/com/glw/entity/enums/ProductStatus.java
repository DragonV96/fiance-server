package com.glw.entity.enums;

/**
 * @author : glw
 * @date : 2019/6/27
 * @time : 11:05
 * @Description: 产品状态
 */
public enum ProductStatus {
    AUDITING("审核中"),
    IN_SELL("销售中"),
    LOCKED("暂停销售"),
    FINISHED("已结束");

    private String desc;

    ProductStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}

package com.glw.entity.enums;

/**
 * @author : glw
 * @date : 2019/6/27
 * @time : 11:25
 * @Description: 订单状态
 */
public enum OrderStatus {
    INIT("初始化"),
    PROCESS("处理中"),
    SUCCESS("成功"),
    FAIL("失败");

    private String desc;

    OrderStatus(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}

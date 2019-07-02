package com.glw.manager.error;

import org.springframework.util.Assert;

/**
 * @author : glw
 * @date : 2019/7/1
 * @time : 14:09
 * @Description : 错误种类
 */
public enum ErrorEnum {
    ID_NOT_NULL("F001", "编号不可为空", false),
    NAME_NOT_NULL("F101", "产品名称不可为空", false),
    THRESHOLD_AMOUNT_NOT_NULL("F202", "起投金额不可为空", false),
    STEP_AMOUNT_NOT_NULL("F303", "投资步长不可为空", false),
    LOCK_TERM_NOT_NULL("F404", "锁定期不可为空", false),
    REWARD_RATE_NOT_NULL("F505", "收益率不可为空", false),
    STATUS_NOT_NULL("F606", "状态不可为空", false),
    UNKNOW("999", "未知异常", false);

    private String code;
    private String message;
    private boolean canRetry;

    ErrorEnum(String code, String message, boolean canRetry) {
        this.code = code;
        this.message = message;
        this.canRetry = canRetry;
    }

    public static ErrorEnum getByCode (String code) {
       for (ErrorEnum errorEnum : ErrorEnum.values()) {
            if (errorEnum.code.equals(code)) {
                return errorEnum;
            }
       }
       return UNKNOW;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isCanRetry() {
        return canRetry;
    }
}

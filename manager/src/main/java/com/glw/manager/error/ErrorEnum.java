package com.glw.manager.error;

/**
 * @author : glw
 * @date : 2019/7/1
 * @time : 14:09
 * @Description : 错误种类
 */
public enum ErrorEnum {
    ID_NOT_NULL("F001", "编号不可为空", false),
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

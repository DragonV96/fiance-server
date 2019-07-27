package com.glw.seller.sign;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.glw.util.JsonUtil;

/**
 * @author : glw
 * @date : 2019/7/27
 * @time : 17:18
 * @Description : 签名明文
 * 为了防止因为字段排序不正确或者字段内容为空导致签名验签不通过，所有传输对象必须实现此接口
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public interface SignText {
    default String toText(){
        return JsonUtil.toJson(this);
    }
}

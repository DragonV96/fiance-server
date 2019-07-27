package com.glw.seller.sign;

import com.glw.seller.service.SignService;
import com.glw.util.RSAUtil;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author : glw
 * @date : 2019/7/27
 * @time : 17:32
 * @Description : 验签AOP
 */
@Component
@Aspect
public class SignAop {

    @Autowired
    private SignService signService;

    /**
     * 验签
     * @param authId    授权编号(一般放置在配置文件或数据库中，现方便起见直接写死)
     * @param sign      签名
     * @param text      明文
     */
    @Before(value = "execution(* com.glw.seller.controller.*.*(..)) && args(authId,sign,text,..)")
    public void verify(String authId, String sign, SignText text) {
        String publicKey = signService.getPublicKey(authId);
        Assert.isTrue(RSAUtil.verify(text.toText(), sign, publicKey), "验签失败");
    }
}

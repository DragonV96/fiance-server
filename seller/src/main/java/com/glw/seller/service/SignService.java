package com.glw.seller.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : glw
 * @date : 2019/7/27
 * @time : 17:37
 * @Description : 签名服务
 */
@Service
public class SignService {
    static Map<String, String> PUBLIC_KEYS = new HashMap<>();
    static {
        PUBLIC_KEYS.put("1000", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDUVRkgM7kMQMfVu5dpIw0qiBI0\n" +
                "Vlb217bDixcFRZahYqKfGrEpnZzXVUFSOwAaVHYipZ+Ze6avRnUqU3IrGs9SG9ay\n" +
                "EZfjcIpxLIEqTpxi06Oxts03CPz5/mAFHxsGJUbmjyPIcCcIg6U+08EU3pF0GOG4\n" +
                "h7xDiDaHk2ly/EP5ZwIDAQAB");
    }

    /**
     * 根据授权编号获取公钥
     * @param authId
     * @return
     */
    public String getPublicKey(String authId) {
        return PUBLIC_KEYS.get(authId);
    }
}

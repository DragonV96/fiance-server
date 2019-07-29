package com.glw.seller.service;

import com.glw.seller.repository.VerifyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

/**
 * @author : glw
 * @date : 2019/7/29
 * @time : 17:50
 * @Description : 对账服务
 */
@Service
public class VerifyService {

    @Autowired
    private VerifyRepository verifyRepository;

    /**
     * 生成某个渠道某天的对账文件
     * @param chanId
     * @param day
     * @return
     */
    public File makeVerificationFile(String chanId, Date day) {

        return null;
    }
}

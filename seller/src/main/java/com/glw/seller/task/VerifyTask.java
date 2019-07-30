package com.glw.seller.task;

import com.glw.seller.enums.ChanEnum;
import com.glw.seller.service.VerifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : glw
 * @date : 2019/7/30
 * @time : 16:29
 * @Description : 定时对账任务
 */
@Component
public class VerifyTask {

    @Autowired
    private VerifyService verifyService;

//    @Scheduled(cron = "0/5 * * * * ? ")
//    public void hello() {
//        System.out.println("每五秒我来了");
//    }

    // 生成对账文件
    @Scheduled(cron = "0 0 1,3,5 * * ? ")
    public void makeVerificationFile() {
        Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        for (ChanEnum chanEnum : ChanEnum.values()) {
            verifyService.makeVerificationFile(chanEnum.getChanId(), yesterday);
        }
    }

    // 对账
    @Scheduled(cron = "0 0 2,4,6 * * ? ")
    public void verify() {
        Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        for (ChanEnum chanEnum : ChanEnum.values()) {
            verifyService.verifyOrder(chanEnum.getChanId(), yesterday);
        }

    }
}

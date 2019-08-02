package com.glw.seller;

import com.glw.seller.repository.OrderRepository;
import com.glw.seller.service.VerifyService;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author : glw
 * @date : 2019/7/30
 * @time : 11:48
 * @Description : 对账测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)   // 按照名称字典顺序执行单元测试
public class VerifyTest {

    @Autowired
    private VerifyService verifyService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    @Qualifier("readorderRepository")
    private OrderRepository readOrderRepository;

    @Autowired
    private OrderRepository backupOrderRepository;

    @Test
    public void makeVerificationTest() {
        Date day = new GregorianCalendar(2018, 11, 30).getTime();
        File file = verifyService.makeVerificationFile("111", day);
        System.out.println(file.getAbsolutePath());
    }

    @Test
    public void saveVerificationTest() {
        Date day = new GregorianCalendar(2018, 11, 30).getTime();
        verifyService.saveChanOrders("111", day);
    }

    @Test
    public void verifyTest() {
        Date day = new GregorianCalendar(2018, 11, 30).getTime();
        System.out.println(String.join(";", verifyService.verifyOrder("111",  day)));
    }

    @Test
    public void queryOrder(){
        System.out.println(orderRepository.findAll());
        System.out.println(readOrderRepository.findAll());
    }
}

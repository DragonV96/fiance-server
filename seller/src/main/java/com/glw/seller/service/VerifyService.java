package com.glw.seller.service;

import com.glw.entity.VerificationOrder;
import com.glw.seller.enums.ChanEnum;
import com.glw.seller.repositorybackup.VerifyRepository;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Value("${verification.rootdir:/opt/verification}")
    private String rootDir;     // 对账文件根目录

    private static String END_LINE = System.getProperty("line.separator", "\n");    // 换行符

    private static DateFormat DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");      // 日期格式化对象

    private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");      // 时间格式化对象

    /**
     * 生成某个渠道某天的对账文件
     * @param chanId
     * @param day
     * @return
     */
    public File makeVerificationFile(String chanId, Date day) {
        File path = getPath(rootDir, chanId, day);
        if (path.exists()) {
            return path;
        }
        try {
            path.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Date start = getStartOfDay(day);
        Date end = add24Hours(start);
        List<String> orders = verifyRepository.queryVerificationOrders(chanId, start, end);
        String content = String.join(END_LINE, orders);
        FileUtil.writeAsString(path, content);
        return path;
    }

    // 创建结束时间
    private Date add24Hours(Date start) {
        return new Date(start.getTime() + 1000 * 60 * 60 * 24);
    }

    // 创建开始时间
    private Date getStartOfDay(Date day) {
        // 构造起止时间
        String start_str = DAY_FORMAT.format(day);
        Date start = null;
        try {
            start = DAY_FORMAT.parse(start_str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return start;
    }

    // 获取对账文件路径
    public File getPath(String rootDir, String chanId, Date day) {
        String name = DAY_FORMAT.format(day) + "-" + chanId + ".txt";
        File path = Paths.get(rootDir, name).toFile();
        return path;
    }

    /**
     * 按照顺序解析字符串创建对账订单
     * @param line
     * @return
     */
    public static VerificationOrder parseLine(String line) {
        VerificationOrder order = new VerificationOrder();
        String[] props = line.split("\\|");
        order.setOrderId(props[0]);
        order.setOuterOrderId(props[1]);
        order.setChanId(props[2]);
        order.setChanUserId(props[3]);
        order.setProductId(props[4]);
        order.setOrderType(props[5]);
        order.setAmount(new BigDecimal(props[6]));
        try {
            order.setCreateAt(DATE_FORMAT.parse(props[7]));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return order;
    }

    /**
     * 保存渠道订单数据
     * @param chanId
     * @param day
     */
    public void saveChanOrders(String chanId, Date day) {
        ChanEnum conf = ChanEnum.getByChanId(chanId);
        // 根据配置从ftp下载对账的对账数据
        File file = getPath(conf.getRootDir(), chanId, day);
        if (!file.exists()) {
            return;
        }
        String content = null;
        try {
            content = FileUtil.readAsString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines = content.split(END_LINE);
        List<VerificationOrder> orders = new ArrayList<>();
        for (String line : lines) {
            orders.add(parseLine(line));
        }
        verifyRepository.save(orders);
    }

    public List<String> verifyOrder(String chanId, Date day) {
        List<String> errors = new ArrayList<>();
        Date start = getStartOfDay(day);
        Date end = add24Hours(start);
        List<String> excessOrders = verifyRepository.queryExcessOrders(chanId, start, end);
        List<String> missOrders = verifyRepository.queryMissOrders(chanId, start, end);
        List<String> differentOrders = verifyRepository.queryDifferentOrders(chanId, start, end);

        errors.add("长款订单号：" + String.join(",", excessOrders));
        errors.add("漏单订单号：" + String.join(",", missOrders));
        errors.add("不一致订单号：" + String.join(",", differentOrders));

        return errors;
    }
}

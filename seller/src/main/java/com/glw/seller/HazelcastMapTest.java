package com.glw.seller;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author : glw
 * @date : 2019/7/25
 * @time : 14:43
 * @Description : 测试Hazelcast
 */
@Component
public class HazelcastMapTest {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @PostConstruct
    public void put() {
        Map map = hazelcastInstance.getMap("glw");
        map.put("name", "glw");
    }
}

package com.brycehan.shardingsphere.jdbc;

import com.brycehan.shardingsphere.jdbc.entity.Order;
import com.brycehan.shardingsphere.jdbc.entity.User;
import com.brycehan.shardingsphere.jdbc.mapper.OrderMapper;
import com.brycehan.shardingsphere.jdbc.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

/**
 * 垂直分片（垂直分库）测试
 *
 * @author Bryce Han
 * @since 2024/6/27
 */
@SuppressWarnings("unused")
@SpringBootTest
public class VerticalShardingTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 垂直分片：插入数据测试
     */
    @Test
    public void testInsertOrderAndUser(){
        User user = new User();
        user.setUname("Bryce Han");
        user.setId(System.currentTimeMillis());
        userMapper.insert(user);

        Order order = new Order();
        order.setId(System.currentTimeMillis());
        order.setOrderNo("ord_001");
        order.setUserId(user.getId());
        order.setAmount(new BigDecimal(100));
        orderMapper.insert(order);
    }

    /**
     * 垂直分片：查询数据测试
     */
    @Test
    public void testSelectFromOrderAndUser(){
        User user = userMapper.selectById(1L);
        Order order = orderMapper.selectById(1L);
    }

}

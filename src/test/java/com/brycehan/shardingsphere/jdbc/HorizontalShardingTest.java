package com.brycehan.shardingsphere.jdbc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.brycehan.shardingsphere.jdbc.entity.*;
import com.brycehan.shardingsphere.jdbc.mapper.DictMapper;
import com.brycehan.shardingsphere.jdbc.mapper.OrderItemMapper;
import com.brycehan.shardingsphere.jdbc.mapper.OrderMapper;
import com.brycehan.shardingsphere.jdbc.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

/**
 * 水平分片（水平分库、水平分表）测试
 *
 * @author Bryce Han
 * @since 2024/6/27
 */
@Slf4j
@SuppressWarnings("unused")
@SpringBootTest
public class HorizontalShardingTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private DictMapper dictMapper;

    /**
     * 水平分片：分库分表插入数据测试
     */
    @Test
    public void testInsertOrder(){
        for (int i = 1; i < 5; i++) {
            Order order = new Order();
            order.setId(System.currentTimeMillis());
            order.setOrderNo("ORD-00" + i);
            order.setUserId(1L);
            order.setAmount(new BigDecimal(100));
            orderMapper.insert(order);
        }

        for (int i = 5; i < 9; i++) {
            Order order = new Order();
            order.setId(System.currentTimeMillis());
            order.setOrderNo("ORD-00" + i);
            order.setUserId(2L);
            order.setAmount(new BigDecimal(100));
            orderMapper.insert(order);
        }
    }

    /**
     * 测试哈希取模
     */
    @Test
    public void testHash(){
        log.info("ORD-001 hash：{}", Math.abs("ORD-001".hashCode()) % 2);
        log.info("ORD-002 hash：{}", Math.abs("ORD-002".hashCode()) % 2);
    }

    /**
     * 水平分片：分库分表查询所有数据测试
     * 查询了两个数据源，每个数据源中使用 union all 连接两个表
     */
    @Test
    public void testShardingSelectAll(){
        List<Order> orderList = this.orderMapper.selectList(null);
        orderList.forEach(order -> log.info("order: {}", order));
    }

    /**
     * 水平分片：根据user_id查询记录
     * 查询了一个数据源，每个数据源中使用 union all 连接两个表
     */
    @Test
    public void testShardingSelectByUserId(){
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUserId, 1L);
        List<Order> orderList = this.orderMapper.selectList(queryWrapper);
        orderList.forEach(System.out::println);
    }

    /**
     * 测试关联表的插入
     */
    @Test
    public void testInsertOrderAndOrderItem(){
        for (long i = 1; i < 5; i++) {
            Order order = new Order();
            order.setId(System.currentTimeMillis());
            order.setOrderNo("ord-000" + i);
            order.setUserId(1L);
            orderMapper.insert(order);

            for (int j = 0; j < 3; j++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(System.currentTimeMillis());
                orderItem.setOrderNo("ord-000" + i);
                orderItem.setUserId(1L);
                orderItem.setPrice(new BigDecimal(10));
                orderItem.setCount(2);
                orderItemMapper.insert(orderItem);
            }
        }

        for (long i = 5; i < 9; i++) {
            Order order = new Order();
            order.setId(System.currentTimeMillis());
            order.setOrderNo("ord-000" + i);
            order.setUserId(2L);
            orderMapper.insert(order);

            for (int j = 0; j < 3; j++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(System.currentTimeMillis());
                orderItem.setOrderNo("ord-000" + i);
                orderItem.setUserId(2L);
                orderItem.setPrice(new BigDecimal(3));
                orderItem.setCount(3);
                orderItemMapper.insert(orderItem);
            }
        }
    }

    /**
     * 测试关联表的查询
     */
    @Test
    public void testGetOrderAmount(){
        List<OrderVo> orderAmountList = this.orderMapper.getOrderAmount();
        orderAmountList.forEach(System.out::println);
    }

    /**
     * 广播表：测试插入
     */
    @Test
    public void testInsertBroadcast(){
        Dict dict = new Dict();
        dict.setDictType("test");
        this.dictMapper.insert(dict);
    }

    /**
     * 广播表：测试查询
     */
    @Test
    public void testSelectBroadcast(){
        List<Dict> dictList = this.dictMapper.selectList(null);
        this.dictMapper.selectList(null);
        this.dictMapper.selectList(null);
        this.dictMapper.selectList(null);
        dictList.forEach(System.out::println);
    }

}

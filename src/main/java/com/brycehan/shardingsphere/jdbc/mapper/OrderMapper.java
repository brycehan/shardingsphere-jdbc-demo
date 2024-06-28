package com.brycehan.shardingsphere.jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brycehan.shardingsphere.jdbc.entity.Order;
import com.brycehan.shardingsphere.jdbc.entity.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select(value = """
        select o.order_no, sum(i.price * i.count) as amount
        from t_order o
        join t_order_item i on o.order_no = i.order_no
        group by o.order_no
    """)
    List<OrderVo> getOrderAmount();

}

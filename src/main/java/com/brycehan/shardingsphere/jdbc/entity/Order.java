package com.brycehan.shardingsphere.jdbc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("t_order")
public class Order {

    //当配置了shardingsphere-jdbc的分布式序列时，自动使用shardingsphere-jdbc的分布式序列
    //当没有配置shardingsphere-jdbc的分布式序列时，自动依赖数据库的主键自增策略
//    @TableId(type = IdType.AUTO)
//    @TableId(type = IdType.ASSIGN_ID)//分布式id（默认值）
    @TableId(type = IdType.NONE)
    private Long id;
    private String orderNo;
    private Long userId;
    private BigDecimal amount;
}

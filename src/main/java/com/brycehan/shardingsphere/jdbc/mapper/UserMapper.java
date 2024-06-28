package com.brycehan.shardingsphere.jdbc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brycehan.shardingsphere.jdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
package com.brycehan.shardingsphere.jdbc;

import com.brycehan.shardingsphere.jdbc.entity.User;
import com.brycehan.shardingsphere.jdbc.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.util.json.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 读写分离测试
 *
 * @author Bryce Han
 * @since 2024/6/27
 */
@Slf4j
@SpringBootTest
public class ReadWriteTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 测试读写分离，写入数据
     */
    @Test
    public void testInsert() {
        User user = new User();

        user.setId(System.currentTimeMillis());
        user.setUname("bryce");
        this.userMapper.insert(user);

        user.setId(System.currentTimeMillis());
        user.setUname("tom");
        this.userMapper.insert(user);
    }

    /**
     * 测试读写分离，事务
     * 测试时，没有异常也会回滚事务
     */
    @Test
    @Transactional
    public void testTransaction() {
        User user = new User();
        user.setUname("admin");
        this.userMapper.insert(user);

        List<User> users = this.userMapper.selectList(null);
        log.info("users: {}", JsonUtils.toJsonString(users));
    }

    /**
     * 测试读写分离，查询数据
     */
    @Test
    @SuppressWarnings("unused")
    public void testSelect() {
        List<User> users1 = this.userMapper.selectList(null);
        List<User> users2 = this.userMapper.selectList(null);
        List<User> users3 = this.userMapper.selectList(null);
        List<User> users4 = this.userMapper.selectList(null);
    }
}

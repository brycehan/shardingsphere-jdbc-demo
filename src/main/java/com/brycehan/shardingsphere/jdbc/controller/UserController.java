package com.brycehan.shardingsphere.jdbc.controller;

import com.brycehan.shardingsphere.jdbc.entity.User;
import com.brycehan.shardingsphere.jdbc.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    @SuppressWarnings("unused")
    @GetMapping("selectAll")
    public void selectAll() {
        List<User> users = userMapper.selectList(null);
    }
}

package com.xrq.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xrq.mybatisplus.dao.UserMapper;
import com.xrq.mybatisplus.entity.User;
import com.xrq.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {




    public void servicefindList(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.ge("age",40);
        Page<User> page = new Page<>(1, 5,false);
        List<User> userList = baseMapper.findList(page,queryWrapper);
        userList.forEach(System.out::println);

    }
}

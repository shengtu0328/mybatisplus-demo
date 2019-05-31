package com.xrq.mybatisplus;

import com.xrq.mybatisplus.dao.UserMapper;
import com.xrq.mybatisplus.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void select(){
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5,userList.size());
        userList.forEach(System.out::println);
    }


    @Test
    public void insert(){
        User user=new User();                               //id如果不设置 默认填充一个基于雪花算法的自增id
        user.setRealName("xrq5");
        user.setAge(23);
        user.setManagerId(1088248166370832385l);             //默认数据库中的列名_  对应 实体类属性的驼峰
        user.setCreateTime(LocalDateTime.now());

        user.setRemark("备注111");
        int rows= userMapper.insert(user);
        System.out.println("影响记录数"+rows);
    }
}

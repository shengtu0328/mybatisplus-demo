package com.xrq.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xrq.mybatisplus.dao.UserMapper;
import com.xrq.mybatisplus.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void updateById() {
        User user= new User();
        user.setUserId(1087982257332887553l);
        user.setAge(41);
        user.setEmail("UPDATE EMAIL");
        int rows = userMapper.updateById(user);
        System.out.println("影响记录数"+rows);
    }


    @Test
    public void update() {
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<User>();
        updateWrapper.eq("name","李艺伟").eq("age",28); //UpdateWrapper是匹配条件  where


        User user= new User();
        user.setEmail("UPDATE EMAIL");//user是修改的内容   实体中不为null的变量会出现在set中
        int rows = userMapper.update(user,updateWrapper);
        System.out.println("影响记录数"+rows);
    }

    @Test
    public void update2() {

        User whereUser= new User();
        whereUser.setRealName("李艺伟");
        //这样会重复添加条件判断 name='李艺伟'

        UpdateWrapper<User> updateWrapper=new UpdateWrapper<User>(whereUser);
        updateWrapper.eq("name","李艺伟").eq("age",28); //UpdateWrapper是匹配条件  where


        User user= new User();
        user.setEmail("UPDATE EMAIL");//user是修改的内容   实体中不为null的变量会出现在set中
        int rows = userMapper.update(user,updateWrapper);
        System.out.println("影响记录数"+rows);
    }


    @Test
    public void update3() {
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<User>();
        updateWrapper.eq("name","李艺伟").eq("age",28)
        .set("age","33");

        int rows = userMapper.update(null,updateWrapper);
        System.out.println("影响记录数"+rows);
    }

    @Test
    public void updateLambda() {
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<User>();
        updateWrapper.eq("name","李艺伟").eq("age",28)
                .set("age","33");

        int rows = userMapper.update(null,updateWrapper);
        System.out.println("影响记录数"+rows);
    }
}

package com.xrq.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xrq.mybatisplus.dao.UserMapper;
import com.xrq.mybatisplus.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    //根据主键查询单个对象
    public void selectById(){
        User user = userMapper.selectById(1094590409767661570l);
        System.out.println(user);
    }

    @Test
    //根据主键集合查询对象集合
    public void selectByIds(){
        List<Long> idList = Arrays.asList(1094590409767661570l, 1088248166370832385l);
        List<User> userList = userMapper.selectBatchIds(idList);
        userList.forEach(System.out::println);
    }

    @Test
    //根据map条件查询对象集合
    public void selectByMap(){
        Map<String,Object> columnMap=new HashMap<>();
        columnMap.put("name","王天风");
        //columnMap.put("realName","王天风");//（map主的key为数据库的列名，不是实体中的属性名）
        columnMap.put("age",25);
        List<User> userList = userMapper.selectByMap(columnMap);
        userList.forEach(System.out::println);
    }

    @Test
    //条件构造器查询
    //name like ‘%雨%’ and age<40
    public void selectByWrapper(){
        QueryWrapper<User> queryWrapper= new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();

        queryWrapper.like("name","雨").lt("age",40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
    //条件构造器查询
    //name like ‘%雨%’ and age between 20 and 40 and email is not null
    public void selectByWrapper2(){
        QueryWrapper<User> queryWrapper= new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();

        queryWrapper.like("name","雨").between("age",20,40).isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }



    @Test
    //条件构造器查询
    //name like ‘王%’ or age >= 25 order by age desc,id asc
    public void selectByWrapper3(){
        QueryWrapper<User> queryWrapper= new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();

        queryWrapper.likeRight("name","王")
                .or()
                .ge("age",25).orderByDesc("age").orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }
}

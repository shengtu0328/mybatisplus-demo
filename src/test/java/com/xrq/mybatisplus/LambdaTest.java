package com.xrq.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class LambdaTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    // select不列出全部字段，返回map，并且查那些字段就只放这些字段到map中,不会有多余的字段
    public void selectByLambda() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();

        queryWrapper.select("id","name").like("name", "雨").lt("age", 40);

        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }





}

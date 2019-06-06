package com.xrq.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xrq.mybatisplus.dao.UserMapper;
import com.xrq.mybatisplus.entity.User;
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
public class SelectMapsTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    // select不列出全部字段，返回map，并且查那些字段就只放这些字段到map中,不会有多余的字段
    public void selectByMaps() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();

        queryWrapper.select("id","name").like("name", "雨").lt("age", 40);

        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    // select avg(age) avg_age,
    //        min(age) min_age,
    //        max(age) max_age
    //        from user
    //        group by manager_id
    //        having sum(age) <500
    public void selectByMap2() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();

        queryWrapper.select("avg(age) avg_age","min(age) min_age","max(age) max_age")
                    .groupBy("manager_id")
                    .having("sum(age) <{0}",5000);

        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }






    @Test
    // selectObjs不列出全部字段，返回map，并且查那些字段就只放这些字段到map中,不会有多余的字段,并且只显示第一列的结果
    public void selectObjs() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();

        queryWrapper.select("id","name").like("name", "雨").lt("age", 40);

        List<Object> list = userMapper.selectObjs(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    // selectOne  如果查到大于一条的就会报错
    public void selectOne() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();

        queryWrapper.like("name", "红雨").lt("age", 40);

        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }
}

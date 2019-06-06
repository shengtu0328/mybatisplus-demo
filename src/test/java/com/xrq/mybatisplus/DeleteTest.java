package com.xrq.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xrq.mybatisplus.dao.UserMapper;
import com.xrq.mybatisplus.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Wrapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteById() {
        int rows = userMapper.deleteById(1135484904460828673l);
        System.out.println("影响记录数"+rows);
    }


    @Test
    public void deleteByMap() {

        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name","xrq5");   // 这种条件匹配方式在where条件中是=
        columnMap.put("age","28");
        int rows = userMapper.deleteByMap(columnMap);
        System.out.println("影响记录数"+rows);
    }

    @Test
    public void deleteBatchIds() {
        int rows = userMapper.deleteBatchIds(Arrays.asList(1135469818023297026l,1134393107680653314l,1134391744263417858l));
        System.out.println("影响记录数"+rows);
    }


    @Test
    public void deleteByWrapperLambda() {
        LambdaQueryWrapper<User> userLambdaQueryWrapper = Wrappers.<User>lambdaQuery();
        userLambdaQueryWrapper.eq(User::getAge,27);
        int rows = userMapper.delete(userLambdaQueryWrapper);
        System.out.println("影响记录数"+rows);
    }

    @Test
    public void deleteByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.in("age",Arrays.asList(44 ,45,46));

        int rows = userMapper.delete(queryWrapper);
        System.out.println("影响记录数"+rows);
    }
}

package com.xrq.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class PageTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.ge("age",22);
        Page<User> page = new Page<>(2, 5);    //current是第几页 size是每页打大小

        IPage<User> IPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("总页数" + IPage.getPages());
        System.out.println("总记录数" + IPage.getTotal());
        List<User> userList = IPage.getRecords();
        userList.forEach(System.out::println);
    }

    @Test
    public void selectMapsPage() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.ge("age",22);
        Page<User> page = new Page<>(2, 5);

        IPage<Map<String, Object>> IPageMap = userMapper.selectMapsPage(page, queryWrapper);

        System.out.println("总页数" + IPageMap.getPages());
        System.out.println("总记录数" + IPageMap.getTotal());
        List<Map<String, Object>> list = IPageMap.getRecords();
        list.forEach(System.out::println);
    }


    @Test
    public void selectPageFalse() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.ge("age",22);
        Page<User> page = new Page<>(2, 5,false);  //传false  不会去查总记录数

        IPage<User> IPage = userMapper.selectPage(page, queryWrapper);
        System.out.println("总页数" + IPage.getPages());
        System.out.println("总记录数" + IPage.getTotal());
        List<User> userList = IPage.getRecords();
        userList.forEach(System.out::println);
    }

    @Test
    public void findList() {        //自定义的分页查询 如果用QueryWrapper条件，则在xml中 需要加上${ew.customSqlSegment} ，不然QueryWrapper无效
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.ge("age",40);
        Page<User> page = new Page<>(1, 5,false);

        List<User> userList = userMapper.findList(page,queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void findList2() {  //自定义的分页查询 如果用QueryWrapper条件，则在xml中 需要加上${ew.customSqlSegment} ，不然QueryWrapper无效
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.ge("age",40);
        Page<User> page = new Page<>(1, 5,false);

        Page<User> list2 = userMapper.findList2(page, queryWrapper);
        System.out.println(list2.getTotal());

        list2.getRecords().forEach(System.out::println);
    }
}

package com.xrq.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xrq.mybatisplus.dao.UserMapper;
import com.xrq.mybatisplus.entity.User;
import com.xrq.mybatisplus.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {


    @Autowired
    private UserService userService;


    @Test
    public void getOne() {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge,25));//用getOne查询的记录数大于1 会报错
        System.out.println(user);
    }
    @Test
    public void getOneFalse() {
        User user = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge,25),false);//多传一个false，用getOne查询的记录数大于1 不会报错，会有提醒，只显示第一个
        System.out.println(user);
    }

    @Test
    public void saveBatch() {
        User user1 =new User();
        user1.setRealName("小琪");
        user1.setAge(22);

        User user2 =new User();
        user2.setRealName("国玉");
        user2.setAge(22);

        List<User> userList = Arrays.asList(user1, user2);
        boolean b = userService.saveBatch(userList);
        System.out.println(b);
    }


    //saveOrUpdateBatch 主键有值的就修改 ，没值的就是添加
    @Test
    public void saveOrUpdateBatch() {
        User user1 =new User();
        user1.setRealName("小琪2");
        user1.setAge(22);

        User user2 =new User();
        user2.setUserId(1136464689907384332l);
        user2.setRealName("山本国玉");
        user2.setAge(22);

        List<User> userList = Arrays.asList(user1, user2);
        boolean b = userService.saveOrUpdateBatch(userList);
        System.out.println(b);
    }

    //Service 层中的lambda调用  不需要再像mapper中 构造一次Wrapper
    @Test
    public void lambdaQuery() {
        List<User> userList = userService.lambdaQuery().gt(User::getAge, 20).like(User::getRealName, "雨").list();
        userList.forEach(System.out::println);
    }

    @Test
    public void lambdaUpdate() {
        boolean b = userService.lambdaUpdate().eq(User::getAge, 25).set(User::getAge, 26).update();
        System.out.println(b);
    }

    @Test
    public void lambdaUpdateremove() {
        boolean b = userService.lambdaUpdate().eq(User::getAge, 28).remove();
        System.out.println(b);
    }


    @Test
    public void servicefindList() {
         userService.servicefindList();
    }



    @Test
    public void page() {
        Page<User> page = new Page<>(1, 5);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("id userId","name realName").like("name", "雨").lt("age", 40);
        IPage<User> Ipage = userService.page(page, queryWrapper);
//        List<User> records = Ipage.getRecords();
//        records.stream().forEach(System.out::println);
//        System.out.println(Ipage);

        Ipage.getRecords().stream().forEach(System.out::println);
    }






    @Test
    public void pageMaps() {
        Page<User> page = new Page<>(1, 5);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.select("id userId","name realName").like("name", "雨").lt("age", 40);
        IPage<Map<String, Object>> Ipage = userService.pageMaps(page, queryWrapper);
//        List<User> records = Ipage.getRecords();
//        records.stream().forEach(System.out::println);
//        System.out.println(Ipage);

        Ipage.getRecords().stream().forEach(System.out::println);
    }


}

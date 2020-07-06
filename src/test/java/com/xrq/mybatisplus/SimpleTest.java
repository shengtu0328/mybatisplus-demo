package com.xrq.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xrq.mybatisplus.config.MybatisPlusConfig;
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
    public void select() {
        MybatisPlusConfig.myTableName.set("user_2001");
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);


        MybatisPlusConfig.myTableName.remove();
        List<User> userList2 = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }


    @Test
    public void insert() {
        User user = new User();                               //id如果不设置 默认填充一个基于雪花算法的自增id
        user.setRealName("xrq66");
        user.setAge(23);
        user.setManagerId(1088248166370832385l);             //默认数据库中的列名_  对应 实体类属性的驼峰
//        user.setCreateTime(LocalDateTime.now());

        user.setRemark("备注111");
        int rows = userMapper.insert(user);
        System.out.println("影响记录数" + rows);
        System.out.println("主键id" + user.getUserId());

    }

    @Test
    //根据主键查询单个对象
    public void selectById() {
        User user = userMapper.selectById(1094590409767661570l);
        System.out.println(user);
    }

    @Test
    //根据主键集合查询对象集合
    public void selectByIds() {
        List<Long> idList = Arrays.asList(1094590409767661570l, 1088248166370832385l);
        List<User> userList = userMapper.selectBatchIds(idList);
        userList.forEach(System.out::println);
    }

    @Test
    //根据map条件查询对象集合
    public void selectByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "王天风");
        //columnMap.put("realName","王天风");//（map主的key为数据库的列名，不是实体中的属性名）
        columnMap.put("age", 25);
        List<User> userList = userMapper.selectByMap(columnMap);
        userList.forEach(System.out::println);
    }

    @Test
    //条件构造器查询
    //name like ‘%雨%’ and age<40
    public void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();

        queryWrapper.like("name", "雨").lt("age", 40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
    //条件构造器查询
    //name like ‘%雨%’ and age between 20 and 40 and email is not null
    public void selectByWrapper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();
        //key为数据库的列名，不是实体中的属性名

        queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
    //条件构造器查询
    //name like ‘王%’ or age >= 25 order by age desc,id asc
    public void selectByWrapper3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();
        //key为数据库的列名，不是实体中的属性名
        queryWrapper.likeRight("name", "王")
                .or()
                .ge("age", 25).orderByDesc("age").orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
    //条件构造器查询  数据库函数 创建日期为2019年2月14 并且 直属上级名字为王姓
    //date_format(create_time,'%Y-%m-%d') and  manager_id in (select id from t_user where name like '王%')
    public void selectByWrapper4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();
        //key为数据库的列名，不是实体中的属性名
        queryWrapper
                .apply("date_format(create_time,'%Y-%m-%d') ={0}", "2019-02-14")  //这种方式不会sql注入
                //.apply("date_format(create_time,'%Y-%m-%d') ='2019-02-14'") //这样写有可能会引起sql注入 比如 '2019-02-14' or true or true,会查出全部记录
                .inSql(" manager_id", "select id from t_user where name like '王%'");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
    //条件构造器查询
    //name like ‘王%’ and (age<40 or email is not null)
    public void selectByWrapper5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();
        //key为数据库的列名，不是实体中的属性名
        queryWrapper.likeRight("name", "王")
                .and(wq -> wq.lt("age", 10).or().isNotNull("email"));


        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
    //条件构造器查询
    //name like ‘王%’ or (age<40 and age>20 and email is not null)
    public void selectByWrapper6(){
        QueryWrapper<User> queryWrapper= new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();
        //key为数据库的列名，不是实体中的属性名
        queryWrapper.likeRight("name","王")
                .or(wq->wq.lt("age",40).gt("age",20).isNotNull("email"));


        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
    //条件构造器查询
    //  (age<40 or email is not null) and name like ‘王%’
    public void selectByWrapper7() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();
        //key为数据库的列名，不是实体中的属性名
        queryWrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email"))
                .likeRight("name", "王");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
    //条件构造器查询
    //  age in (30 ,31,34,40)
    public void selectByWrapper8() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();
        //key为数据库的列名，不是实体中的属性名
        queryWrapper.in("age",Arrays.asList(30 ,31,34,40));

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }



    @Test
    //条件构造器查询
    //  limit 1
    public void selectByWrapper9() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();
        //key为数据库的列名，不是实体中的属性名
        queryWrapper.in("age",Arrays.asList(30 ,31,34,40)).last("limit 1");

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
    //条件构造器查询 select不列出全部字段
    //name like ‘%雨%’ and age<40
    public void selectByWrapperSelect() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();
        //select的列名如果和属性名不一致 可以这么写,但是没有查询的列映射到属性会变成null
        queryWrapper.select("id userId","name realName","email").like("name","雨").lt("age",40);

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }
    @Test
    //条件构造器查询 select不列出全部字段
    public void selectByWrapperSelect2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();
        //key为数据库的列名，不是实体中的属性名
        queryWrapper.select(User.class,tableFieldInfo -> !tableFieldInfo.getColumn().equals("create_time")&&
                !tableFieldInfo.getColumn().equals("manager_id"));//查的时候排除create_time和manager_id

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    //条件构造器查询 select不列出全部字段 Condition
    public void selectByWrapperCondition() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        //QueryWrapper<User> query= Wrappers.query();
        //key为数据库的列名，不是实体中的属性名

        String name="王";
        String email="";

        queryWrapper.like(StringUtils.isNotEmpty(name),"name",name)
                .like(StringUtils.isNotEmpty(email),"email",email);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }




    @Test
    //条件构造器查询
    //name like ‘%雨%’ and age<40
    public void selectByWrapperEntity() {

        User whereUser =new User();
        whereUser.setRealName("刘红雨"); //但默认这样写是按照=来查询，如果想要like，可以在实体类属性的@TableField 中设置condition = SqlCondition.LIKE
        whereUser.setAge(32);

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>(whereUser);
        //queryWrapper.like("name", "雨").lt("age", 40); //可以继续条件查询 不冲突
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    @Test //allEq的作用可以把参数map中 key对应的value为null进行处理  true  xx is null  false  去除 xx
    public void selectByWrapperAllEq() {


        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        Map<String,Object> parms= new HashMap<String,Object>();
        parms.put("name","王天风");
        //parms.put("age",25);
        parms.put("age",null);//sql里就是age IS NULL

        queryWrapper.allEq(parms);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }



    @Test
    public void selectByWrapperAllEq2() {


        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        Map<String,Object> parms= new HashMap<String,Object>();
        parms.put("name","王天风");
        //parms.put("age",25);
        parms.put("age",null);

        queryWrapper.allEq(parms,false);//设置为false map中 value为null的会忽略掉
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }


    @Test
    public void selectByWrapperAllEq3() {

        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        Map<String,Object> parms= new HashMap<String,Object>();
        parms.put("name","王天风");
        //parms.put("age",25);
        parms.put("age",null);

        queryWrapper.allEq((k,v)->!k.equals("name"),parms);// 满足条件的（true）才会添加到条件中
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

}

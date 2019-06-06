package com.xrq.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xrq.mybatisplus.dao.UserMapper;
import com.xrq.mybatisplus.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdTypeTest {


/*
    mybatisplus的主键策略在 IdType类中  有6种

    AUTO(0),               数据库id自增 依赖于数据库完成id生成 1.数据库主键设置为自增 2.  @TableId(type= IdType.AUTO) 3.插入时不设置id
    NONE(1),               NONE是默认策略，如果TableId不设置IdType，使用的就是IdType.NONE 如果自己不通过代码设置主键的值，按照全局策略(全局策略默认是雪花算法)来设置,如果自己设置了值，就使用自己设置的值。
    INPUT(2),              用户自己手动设置id（oracle中的序列用的就是这种,如果没有设置，但是数据源有自增设置，那就按照数据库自增id）

    以下三种只有当id对象为空才自动填充（如果id有值，就按照id设置好的值插入，下面三种不会生效）

    ID_WORKER(3),          全局策略(全局策略默认是雪花算法-数值)
    UUID(4),               UUID
    ID_WORKER_STR(5);      全局策略(全局策略默认是雪花算法-字符串)


    如果想要设置全局配置为UUID
    可以在yml配置文件中设置
    mybatis-plus:
     global-config:
       db-config:
         id-type: uuid

    局部设置 高于 全局设置

*/

    @Autowired
    private UserMapper userMapper;


    @Test
    public void insert() {
        User user = new User();

//        user.setUserId(1234568l);
        user.setRealName("xrq5");
        user.setAge(23);
        user.setManagerId(1088248166370832385l);
        user.setCreateTime(LocalDateTime.now());

        user.setRemark("备注111");
        int rows = userMapper.insert(user);
        System.out.println("影响记录数" + rows);
        System.out.println("回写的主键id" + user.getUserId());

    }

}

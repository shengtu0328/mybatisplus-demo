package com.xrq.mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/***
 * 自定义填充公共 name 字段
 */
@Component
public class DateMetaObjectHandler implements MetaObjectHandler {


    /**
     * 插入填充，字段为空自动填充
     */

    private final static String UPDATE_TIME = "updateTime";
    private final static String CREATE_TIME = "createTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}

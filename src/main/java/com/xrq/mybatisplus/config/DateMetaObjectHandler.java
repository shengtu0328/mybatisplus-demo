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
    @Override
    public void insertFill(MetaObject metaObject) {
        //有这个属性才填充
        boolean hasSetter = metaObject.hasSetter("createTime");
        if(hasSetter){
            setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //updateTime属性等于null才填充
        Object o = getFieldValByName("updateTime", metaObject);
        if(o==null){
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}

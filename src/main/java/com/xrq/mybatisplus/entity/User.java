package com.xrq.mybatisplus.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class User {

    //主键
    private Long id;

    //姓名
    private String name;

    //年龄
    private Integer age;

    //邮箱
    private String email;

    //直属上级
    private Long managerId;

    //创建时间
    private LocalDateTime createTime;
}

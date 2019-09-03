package com.xrq.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
//@TableName("t_user")//如果类名和表名相同就可以自动映射（不需要此注解），如果不一样可以使用此注解，并添加属性进行绑定
public class User {

    //主键                //mp默认只会为属性名叫 id 的字段填充主键，如果不是叫id不会为他填充
    @TableId(value="id",type=IdType.INPUT)      //如果此属性对应主键，并且属性和表的主键列名不对应，就可以用@TableId修饰设置绑定关系   ，@TableField 同理
    private Long userId;

    //姓名
    @TableField(value = "name",condition = SqlCondition.LIKE)
    private String realName;

    //年龄
    @TableField(condition ="%s&lt;#{%s}") //小于

    private Integer age;

    //邮箱
    private String email;

    //直属上级
    private Long managerId;

    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(exist = false)//exist = false代表他不是数据库的字段
    private String remark;

    //版本
    private Integer version;

    //逻辑删除表示（0 未删除 1已删除）
    @TableLogic
    private Integer deleted;

}

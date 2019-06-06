package com.xrq.mybatisplus.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xrq.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

//    @Select("select * from user $(ew.customSqlSegment)")
//    List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);//自定义sql


    List<User> findList(Page<User> page,@Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    Page<User> findList2(Page<User> page, @Param(Constants.WRAPPER) QueryWrapper queryWrapper);

}

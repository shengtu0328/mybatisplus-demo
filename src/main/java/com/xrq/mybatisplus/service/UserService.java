package com.xrq.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xrq.mybatisplus.entity.User;

public interface UserService extends IService<User> {

    public void servicefindList();

}

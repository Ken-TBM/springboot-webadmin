package com.kenproject.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kenproject.admin.bean.Account;
import com.kenproject.admin.bean.User;
import com.kenproject.admin.mapper.UserMapper;
import com.kenproject.admin.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

}

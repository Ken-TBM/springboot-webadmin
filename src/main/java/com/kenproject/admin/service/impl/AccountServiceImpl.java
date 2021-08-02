package com.kenproject.admin.service.impl;

import com.kenproject.admin.bean.Account;
import com.kenproject.admin.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl {
    @Autowired
    AccountMapper accountMapper;

    public Account getAcctById(Integer id){
       return accountMapper.getAcct(id);
    }
}

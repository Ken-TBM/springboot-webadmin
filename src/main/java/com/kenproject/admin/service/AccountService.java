package com.kenproject.admin.service;

import com.kenproject.admin.bean.Account;
import org.springframework.stereotype.Service;


public interface AccountService {
    public Account getAcctById(Integer id);
}

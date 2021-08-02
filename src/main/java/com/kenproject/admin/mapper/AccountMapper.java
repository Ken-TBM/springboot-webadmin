package com.kenproject.admin.mapper;

import com.kenproject.admin.bean.Account;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;


@Component
@Mapper
public interface AccountMapper {
    public Account getAcct(Integer id);
}

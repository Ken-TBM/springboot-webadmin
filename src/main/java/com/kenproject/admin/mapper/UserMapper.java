package com.kenproject.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kenproject.admin.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

}

package com.kenproject.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@TableName("user_tabl")
public class User {
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String password;

    //以下是数据库的字段
    private long id;
    private String name;
    private  Integer age;
    private String email;
}

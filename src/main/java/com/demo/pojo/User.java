package com.demo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class User implements Serializable{
    private int id;
    private String telephone;
    private String realname;
    private String password;
    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;
    private String headimg;
    private int available;
    private Dept dept;
    private List<Role> roles;
}

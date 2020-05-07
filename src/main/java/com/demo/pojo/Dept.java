package com.demo.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Dept implements Serializable {
    private int id;
    private String dname;
    private List<User> users;
}

package com.demo.param;

import com.demo.pojo.User;
import lombok.Data;

@Data
public class UserVo extends User{
    private Integer page=1;
    private Integer limit=10;

    public UserVo(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }
}

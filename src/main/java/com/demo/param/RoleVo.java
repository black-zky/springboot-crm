package com.demo.param;

import com.demo.pojo.Role;
import com.demo.pojo.User;
import lombok.Data;

@Data
public class RoleVo extends Role {
    private Integer page=1;
    private Integer limit=10;
    public boolean LAY_CHECKED=false;
}

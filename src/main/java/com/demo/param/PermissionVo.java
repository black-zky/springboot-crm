package com.demo.param;

import com.demo.pojo.Permission;
import com.demo.pojo.Role;
import lombok.Data;

@Data
public class PermissionVo extends Permission {
    private Integer page=1;
    private Integer limit=10;
}

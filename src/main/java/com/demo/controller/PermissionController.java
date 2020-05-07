package com.demo.controller;

import com.demo.param.DataGridView;
import com.demo.param.PermissionVo;
import com.demo.param.RoleVo;
import com.demo.service.PermissionService;
import com.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @GetMapping("/permissions")
    public DataGridView findPermissionsByPage(PermissionVo permissionVo){
        DataGridView dgv = permissionService.selectPermissionsByPage(permissionVo);
        return dgv;
    }
}

package com.demo.controller;

import com.demo.param.DataGridView;
import com.demo.param.RoleVo;
import com.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/roles")
    public DataGridView findRolesByPage(RoleVo roleVo){
        DataGridView dgv = roleService.selectRolesByPage(roleVo);
        return dgv;
    }
}

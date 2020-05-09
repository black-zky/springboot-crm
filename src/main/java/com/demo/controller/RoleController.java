package com.demo.controller;

import com.demo.param.DataGridView;
import com.demo.param.RoleVo;
import com.demo.pojo.Permission;
import com.demo.service.PermissionService;
import com.demo.service.RoleService;
import com.demo.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;

    @GetMapping("/roles")
    public DataGridView findRolesByPage(RoleVo roleVo){
        DataGridView dgv = roleService.selectRolesByPage(roleVo);
        return dgv;
    }

    @GetMapping("roles/findAllPermission.do")
    public List<Permission> findAllPermissions(){
        List<Permission> permissions=permissionService.selectAllPermissions();
        return permissions;
    }

    @GetMapping("roles/getChecked.do")
    public List<Permission> findPermissionsByRid(int rid){
        List<Permission> permissions=roleService.findPermissionsByRid(rid);
        return permissions;
    }

    @GetMapping("/roles/assignRight.do")
    public ResultDto grantRolePermissions(int rid, @RequestParam("perids[]")int[] perids){
        try {
            roleService.grantRolePermissions(rid,perids);
            return ResultDto.ROLE_PERMISSION_GRANT_SUCCESS;
        } catch (Exception e){
            e.printStackTrace();
            return ResultDto.ROLE_PERMISSION_GRANT_FAIL;
        }
    }
}

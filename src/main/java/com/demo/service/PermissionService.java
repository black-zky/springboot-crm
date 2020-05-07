package com.demo.service;

import com.demo.param.DataGridView;
import com.demo.param.PermissionVo;
import com.demo.param.RoleVo;
import com.demo.pojo.Permission;

import java.util.List;

public interface PermissionService {

    public List<Permission> selectAllPermissions();

    public List<Permission> selectMenusByUid(int uid);

    public DataGridView selectPermissionsByPage(PermissionVo permissionVo);
}

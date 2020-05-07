package com.demo.service.impl;

import com.demo.dao.PermissionDao;
import com.demo.param.DataGridView;
import com.demo.param.PermissionVo;
import com.demo.pojo.Permission;
import com.demo.pojo.Role;
import com.demo.service.PermissionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionDao permissionDao;

    @Override
    public List<Permission> selectAllPermissions() {
        List<Permission> permissions = permissionDao.selectAllPermissions();
        return permissions;
    }

    @Override
    public List<Permission> selectMenusByUid(int uid) {
        List<Permission> permissions = permissionDao.selectMenusByUid(uid);
        return permissions;
    }

    @Override
    public DataGridView selectPermissionsByPage(PermissionVo permissionVo) {
        Page<Object> page = PageHelper.startPage(permissionVo.getPage(), permissionVo.getLimit());
        List<Permission> permissions = permissionDao.selectPermissionsByProperty(permissionVo);
        return new DataGridView(page.getTotal(),permissions);
    }
}

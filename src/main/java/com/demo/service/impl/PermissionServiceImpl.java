package com.demo.service.impl;

import com.demo.dao.PermissionDao;
import com.demo.pojo.Permission;
import com.demo.service.PermissionService;
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
}

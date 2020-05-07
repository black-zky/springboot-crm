package com.demo.service;

import com.demo.pojo.Permission;

import java.util.List;

public interface PermissionService {

    public List<Permission> selectAllPermissions();

    public List<Permission> selectMenusByUid(int uid);
}

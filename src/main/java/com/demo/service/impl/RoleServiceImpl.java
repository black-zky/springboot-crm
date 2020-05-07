package com.demo.service.impl;

import com.demo.dao.RoleDao;
import com.demo.pojo.Role;
import com.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> selectAllRoles() {
        List<Role> roles = roleDao.selectAllRoles();
        return roles;
    }

    @Override
    public Set<String> selectRoleNamesByUid(int uid) {
        return null;
    }
}

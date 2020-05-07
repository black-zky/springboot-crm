package com.demo.service;

import com.demo.pojo.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    public List<Role> selectAllRoles();

    public Set<String> selectRoleNamesByUid(int uid);
}

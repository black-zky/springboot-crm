package com.demo.service.impl;

import com.demo.dao.RoleDao;
import com.demo.param.DataGridView;
import com.demo.param.RoleVo;
import com.demo.pojo.Permission;
import com.demo.pojo.Role;
import com.demo.pojo.User;
import com.demo.service.RoleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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

    @Override
    public DataGridView selectRolesByPage(RoleVo roleVo) {
        Page<Object> page = PageHelper.startPage(roleVo.getPage(), roleVo.getLimit());
        List<Role> roles = roleDao.selectRolesByProperty(roleVo);
        return new DataGridView(page.getTotal(),roles);
    }

    @Override
    public List<Permission> findPermissionsByRid(int rid) {
        List<Permission> permissions=roleDao.selectPermissionsByRid(rid);
        return permissions;
    }

    @Override
    public boolean grantRolePermissions(int rid, int[] perids) {
        boolean flag = false;

        roleDao.deleteRolePermissionsByRid(rid);
        if(perids!=null&&perids.length>0){
            for (int perid:perids){
                int res = roleDao.insertRolePermission(rid,perid);
                if(res==0){
                    return false;
                }
                flag=true;
            }
        }
        return flag;
    }
}

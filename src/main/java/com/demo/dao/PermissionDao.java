package com.demo.dao;

import com.demo.param.PermissionVo;
import com.demo.pojo.Permission;
import com.demo.pojo.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionDao {
    public List<Permission> selectAllPermissions();
    /**
     * 通过角色id查询权限集合
     * @param rid
     * @return
     */
    public List<Permission> selectPermissionsByRid(int rid);

    public Set<String> selectPercodesByUid(int uid);

    /**
     * 通过用户id查询菜单的集合
     * @return
     */
    public List<Permission> selectMenusByUid(int uid);

    List<Permission> selectPermissionsByProperty(PermissionVo permissionVo);
}

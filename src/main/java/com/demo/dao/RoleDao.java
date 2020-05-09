package com.demo.dao;

import com.demo.param.RoleVo;
import com.demo.pojo.Permission;
import com.demo.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleDao {
    /**
     * 通过用户id查询角色集合
     * @param uid
     * @return
     */
    public List<Role> selectRolesByUid(int uid);
    /**
     * 查询所有的角色
     * @return
     */
    public List<Role> selectAllRoles();
    /**
     * 通过权限id，查找到角色id的集合
     * @param perid
     * @return
     */
    public List<Role> selectRolesByPerid(int perid);

    public Set<String> selectRoleNamesByUid(int uid);

    List<Role> selectRolesByProperty(RoleVo roleVo);

    List<Permission> selectPermissionsByRid(int rid);

    int deleteRolePermissionsByRid(int rid);

    int insertRolePermission(@Param("rid") int rid, @Param("perid") int perid);
}

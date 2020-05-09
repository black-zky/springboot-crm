package com.demo.service.impl;

import com.demo.dao.PermissionDao;
import com.demo.dao.RoleDao;
import com.demo.dao.UserDao;
import com.demo.param.DataGridView;
import com.demo.param.UserVo;
import com.demo.pojo.Role;
import com.demo.pojo.User;
import com.demo.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public User login(String telephone, String password) {
        User user = userDao.selectUserByTelAndPwd(telephone,password);
        return user;
    }

    @Override
    public User findUserByTel(String telephone) {
        User user = userDao.selectUserByTel(telephone);
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addUser(User user) {
        boolean flag = false;
        userDao.insertUser(user);
        List<Role> roles = user.getRoles();
        if(roles!=null){
            for (Role role: roles) {
                userDao.insertUserRole(user.getId(), role.getId());
            }
        }
        flag=true;
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUesr(int uid, String path) {
        boolean flag =false;
        User user = userDao.selectUserById(uid);
        if(user==null){
            return flag;
        }
        userDao.deleteUserRole(uid);
        userDao.deleteUser(uid);
        File file = new File(path+user.getHeadimg());
        file.delete();
        flag=true;
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUesrs(int[] uids) {
        if(uids!=null && uids.length!=0){
            for (int uid:uids){
                if(!deleteUesr(uid,"")){
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    @Override
    public Set<String> selectRoleNamesByUid(int uid) {
        return roleDao.selectRoleNamesByUid(uid);
    }
    @Override
    public Set<String> selectPercodesByUid(int uid) {
        return permissionDao.selectPercodesByUid(uid);
    }

    @Override
    public DataGridView getUserListByPage(UserVo userVo) {
        Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getLimit());
        List<User> users = userDao.selectUsersByProperty(userVo);
        return new DataGridView(page.getTotal(),users);
    }

    @Override
    public List<Role> findRolesByUid(int uid) {
        List<Role> roles = userDao.selectRolesByUid(uid);
        return roles;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addRolesByUid(int uid, int[] rids) {
        boolean flag = false;
        if(rids!=null&&rids.length>0){
            for (int rid:rids){
                int res = userDao.insertUserRole(uid, rid);
                if(res==0){
                    return false;
                }
                flag=true;
            }
        }
        return flag;
    }

    @Override
    public boolean deleteRolesByUid(int uid) {
        int res = userDao.deleteRolesByUid(uid);
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean grantRoles(int uid, int[] rids) {
        return deleteRolesByUid(uid) && addRolesByUid(uid,rids);
    }
}

package com.demo.service;

import com.demo.param.DataGridView;
import com.demo.param.UserVo;
import com.demo.pojo.User;

import java.util.Set;

public interface UserService {
    /**
     * 登录
     * @param telephone
     * @param password
     * @return
     */
    public User login(String telephone, String password);

    public boolean addUser(User user);

    public boolean deleteUesr(int uid, String path);

    public boolean deleteUesrs(int[] uids);

    public User findUserByTel(String telephone);

    Set<String> selectRoleNamesByUid(int uid);

    Set<String> selectPercodesByUid(int uid);

    /**
     * 用户全查询（带分页功能）
     * @param userVo
     * @return
     */
    public DataGridView getUserListByPage(UserVo userVo);
}

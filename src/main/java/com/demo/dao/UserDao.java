package com.demo.dao;

import com.demo.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    public List<User> selectAllUsers();
    /**
     * 通过角色的Id查询到用户的集合
     * @param rid
     * @return
     */
    public List<User> selectUsersByRid(int rid);

    /**
     * 通过用户名和密码进行查询用户对象
     * 通过用户名和密码进行查询用户对象
     * @param telephone
     * @param password
     * @return
     */
    public User selectUserByTelAndPwd(@Param("telephone") String telephone, @Param("password") String password);

    /**
     * 向sys_user表中添加数据
     * @param user
     * @return
     */
    public int insertUser(User user);
    /**
     * 向sys_user_role中间表增加数据
     * @param uid
     * @param rid
     */
    public int insertUserRole(@Param("uid") int uid, @Param("rid") int rid);

    public User selectUserById(int uid);

    public int deleteUser(int uid);

    public int deleteUserRole(int uid);

    public User selectUserByTel(String telephone);

    /**
     * 按条件查询多条用户信息
     * @param user
     * @return
     */
    public List<User> selectUsersByProperty(User user);
}


package com.demo.controller;

import com.demo.param.DataGridView;
import com.demo.param.RoleVo;
import com.demo.pojo.Role;
import com.demo.pojo.User;
import com.demo.service.RoleService;
import com.demo.service.UserService;
import com.demo.utils.ResultDto;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @PostMapping("/users")
    public ResultDto addUser(User user){
        logger.debug("***********addUser*****************");
        logger.debug("电话："+user.getTelephone());
        logger.debug("姓名:"+user.getRealname());
        logger.debug("密码:"+user.getPassword());
        logger.debug("生日:"+user.getBirthday());
        logger.debug("上传图片的路径");
        logger.debug("部门编号:"+user.getDept().getDname());
        logger.debug("状态:"+user.getAvailable());
        logger.debug("************************************");

        User user1 = userService.findUserByTel(user.getTelephone());
        if(user1!=null){
            return ResultDto.USER_EXITS;
        }
        Object encryptionPwd=new SimpleHash("MD5",user.getPassword(),null,1024);
        user.setPassword(encryptionPwd.toString());
        boolean flag = userService.addUser(user);
        if (flag){
            return ResultDto.USER_ADD_SUCCESS;
        } else{
            return  ResultDto.USER_ADD_FAIL;
        }
    }

    @DeleteMapping(value = "/users")
    public ResultDto deleteUser(int[] uIds){
        ResultDto dto=new ResultDto();
        if (uIds==null || uIds.length==0){
            dto.setCode(-1);
            dto.setMsg("用户不能为空");
        }
        try {
            userService.deleteUesrs(uIds);
            dto.setCode(200);
            dto.setMsg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            dto.setCode(-1);
            dto.setMsg("删除失败");
        }
        return dto;
    }

    @GetMapping(value = "/users/initRolesByUid")
    public DataGridView initRolesByUid(int uid){
        List<Role> allroles=roleService.selectAllRoles();
        List<Role> roles = userService.findRolesByUid(uid);
        List<RoleVo> roleVos = new ArrayList<>();
        for(Role role: allroles){
            RoleVo roleVo=new RoleVo();
            roleVo.setId(role.getId());
            roleVo.setRolename(role.getRolename());
            for(Role role2: roles){
                if(role2.getId()==role.getId()){
                    roleVo.setLAY_CHECKED(true);
                }
            }
            roleVos.add(roleVo);
        }
        return new DataGridView((long) allroles.size(),roleVos);
    }

    @GetMapping(value = "/users/grantRoles.do")
    public ResultDto grantRoles(int uid, @RequestParam(value = "rids[]") int[] rids){
        try {
            userService.grantRoles(uid,rids);
            return ResultDto.USER_ROLE_GRANT_SUCCESS;
        } catch (Exception e){
            e.printStackTrace();
            return ResultDto.USER_ROLE_GRANT_FAIL;
        }
    }
}

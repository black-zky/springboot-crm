package com.demo.controller;

import com.demo.param.DataGridView;
import com.demo.param.UserVo;
import com.demo.pojo.User;
import com.demo.service.UserService;
import com.demo.utils.ResultDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/login.do")
    public ResultDto login(String telephone,
                           String password,
//                           @RequestParam(value = "isRememberMe", defaultValue = "0") Integer isRememberMe,
                           HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(telephone, password);
//        if(isRememberMe==1){
//            token.setRememberMe(true);
//        }
        try {
            subject.login(token);
            User user = this.userService.findUserByTel(telephone);
            //获取Session对象
            Session session = subject.getSession();
            session.setAttribute("USER_SESSION", user);
            return ResultDto.LOGIN_SUCCESS;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            request.setAttribute("error", "用户名或者密码有误");
            return ResultDto.LOGIN_ERROR_PWD;
        }
    }

    @GetMapping("/users")
    public DataGridView findUsersByPage(UserVo userVo){
        DataGridView dgv = userService.getUserListByPage(userVo);
        return dgv;
    }

//    @GetMapping("/users")
//    public DataGridView findUsersByPage(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit){
//        UserVo userVo = new UserVo(page,limit);
//        DataGridView dgv = userService.getUserListByPage(userVo);
//        return dgv;
//    }
}

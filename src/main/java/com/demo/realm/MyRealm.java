package com.demo.realm;

import com.demo.pojo.User;
import com.demo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public String getName() {
        return "myRealm";
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String telephone = (String) principalCollection.getPrimaryPrincipal();
        User user = userService.findUserByTel(telephone);
        if(user!=null){
            Set<String> roleNames = userService.selectRoleNamesByUid(user.getId());
            Set<String> percodes = userService.selectPercodesByUid(user.getId());
            authorizationInfo.setRoles(roleNames);
            authorizationInfo.setStringPermissions(percodes);
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        SimpleAuthenticationInfo authenticationInfo = null;
        String telephone=(String) authenticationToken.getPrincipal();
        User user=userService.findUserByTel(telephone);
        if(user!=null){
            authenticationInfo=new SimpleAuthenticationInfo(telephone,user.getPassword(),getName());
        }
        return authenticationInfo;
    }
}

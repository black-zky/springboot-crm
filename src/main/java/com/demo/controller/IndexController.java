package com.demo.controller;

import com.demo.pojo.Permission;
import com.demo.pojo.TreeNode;
import com.demo.pojo.User;
import com.demo.service.PermissionService;
import com.demo.utils.TreeNodeBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/index.do")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/loadIndexLeftMenu.do")
    @ResponseBody
    public List<TreeNode> loadIndexLeftMenu(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        User user = (User) session.getAttribute("USER_SESSION");
        List<Permission> permissions = permissionService.selectMenusByUid(user.getId());
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Permission permission : permissions) {
            boolean spread = permission.getOpen()==1?true:false;
            TreeNode treeNode = new TreeNode(permission.getId(), permission.getPid(),permission.getName(),permission.getIcon(),permission.getHref(),spread);
            treeNodes.add(treeNode);
        }
        treeNodes = TreeNodeBuilder.build(treeNodes, 1);
        return treeNodes;
    }

    @RequestMapping(value = "/toUserManager.do")
    public String toUserManager(){
        return "user/userList";
    }

    @RequestMapping(value = "/toRoleManager.do")
    public String toRoleManager(){
        return "role/roleList";
    }

    @RequestMapping(value = "/toPermissionManager.do")
    public String toPermissionManager(){
        return "per/perList";
    }
}
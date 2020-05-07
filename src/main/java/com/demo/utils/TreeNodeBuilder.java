package com.demo.utils;

import com.demo.pojo.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {
    public static List<TreeNode> build(List<TreeNode> treeNodes, Integer topPid){
        List<TreeNode> nodes = new ArrayList<>();
        for (TreeNode n1 : treeNodes){
            if(n1.getPid()==topPid){
                nodes.add(n1);
            }
            for (TreeNode n2 : treeNodes){
                if (n1.getId()==n2.getPid()){
                    n1.getChildren().add(n2);
                }
            }
        }

        return nodes;
    }

    public static List<TreeNode> build(List<TreeNode> treeNodes){
        List<TreeNode> nodes = new ArrayList<>();
        for (TreeNode n1 : treeNodes){
            boolean isroot=true;
            for (TreeNode n2 : treeNodes){
                if (n1.getPid()==n2.getId()){
                    n2.getChildren().add(n1);
                    isroot=false;
                }
            }
            if(isroot){
                nodes.add(n1);
            }
        }

        return nodes;
    }
}

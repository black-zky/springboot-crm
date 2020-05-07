package com.demo.service.impl;

import com.demo.dao.DeptDao;
import com.demo.pojo.Dept;
import com.demo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    DeptDao deptDao;

    @Override
    public List<Dept> selectAllDepts() {
        List<Dept> depts = deptDao.selectAllDepts();
        return depts;
    }

    @Override
    public Dept findDeptById(int id) {
        Dept dept = deptDao.selectDeptByid(id);
        return dept;
    }

    @Override
    public boolean addDept(Dept dept) {
        int res = deptDao.insertDept(dept);
        if(res==0){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteDept(int id) {
        int res = deptDao.deleteDeptById(id);
        if(res==0){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateDept(Dept dept) {
        int res = deptDao.updateDept(dept);
        if(res==0){
            return false;
        }
        return true;
    }
}

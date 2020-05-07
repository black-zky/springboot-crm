package com.demo.service;

import com.demo.pojo.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> selectAllDepts();

    public Dept findDeptById(int id);

    public boolean addDept(Dept dept);

    public boolean deleteDept(int id);

    public boolean updateDept(Dept dept);
}

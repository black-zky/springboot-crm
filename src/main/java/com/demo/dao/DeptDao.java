package com.demo.dao;

import com.demo.pojo.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptDao {
    /**
     * 通过id查询部门
     *
     * @param id
     * @return
     */
    public Dept selectDeptByid(int id);

    /**
     * 查询所有部门
     *
     * @return
     */
    public List<Dept> selectAllDepts();

    public int deleteDeptById(int id);

    public int insertDept(Dept dept);

    public int updateDept(Dept dept);
}

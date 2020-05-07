package com.demo.controller;

import com.demo.pojo.Dept;
import com.demo.service.DeptService;
import com.demo.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    DeptService deptService;

    @GetMapping("/depts")
    public ResultDto selectAllDepts(){
        ResultDto resultDto = new ResultDto();
        List<Dept> depts = deptService.selectAllDepts();
        if(depts!=null && depts.size()>0){
            resultDto.setCode(200);
            resultDto.setMsg("查询数据成功");
            resultDto.setData(depts);
        } else{
            resultDto.setData(10001);
            resultDto.setMsg("查询数据失败");
        }

        return  resultDto;
    }

    @GetMapping("/depts/{id}")
    public ResultDto selectDeptById(@PathVariable int id){
        ResultDto resultDto = new ResultDto();
        Dept dept = deptService.findDeptById(id);
        if(dept!=null){
            resultDto.setCode(200);
            resultDto.setMsg("查询数据成功");
            resultDto.setData(dept);
        } else{
            resultDto.setData(10001);
            resultDto.setMsg("查询数据失败");
        }

        return  resultDto;
    }

    @PostMapping("/depts")
    public ResultDto addDept(@RequestBody Dept dept){
        ResultDto resultDto = new ResultDto();
        boolean res=deptService.addDept(dept);
        if(!res){
            resultDto.setCode(200);
            resultDto.setMsg("添加数据成功");
            resultDto.setData(res);
        } else{
            resultDto.setData(10001);
            resultDto.setMsg("添加数据失败");
        }

        return  resultDto;
    }

    @PutMapping("/depts")
    public ResultDto updateDept(@RequestBody Dept dept){
        ResultDto resultDto = new ResultDto();
        boolean res=deptService.updateDept(dept);
        if(!res){
            resultDto.setCode(200);
            resultDto.setMsg("更新数据成功");
            resultDto.setData(res);
        } else{
            resultDto.setData(10001);
            resultDto.setMsg("更新数据失败");
        }

        return  resultDto;
    }

    @DeleteMapping("/depts/{id}")
    public ResultDto deleteDept(@PathVariable int id){
        ResultDto resultDto = new ResultDto();
        boolean res=deptService.deleteDept(id);
        if(!res){
            resultDto.setCode(200);
            resultDto.setMsg("删除数据成功");
            resultDto.setData(res);
        } else{
            resultDto.setData(10001);
            resultDto.setMsg("删除数据失败");
        }

        return  resultDto;
    }
}

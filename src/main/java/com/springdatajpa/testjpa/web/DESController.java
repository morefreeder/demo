package com.springdatajpa.testjpa.web;

import com.springdatajpa.testjpa.model.Emp;
import com.springdatajpa.testjpa.service.DESService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@RestController
@Transactional
@Api(value = "数据库操作",description = "关于emp,dept,salgrade等表的数据库操作")
public class DESController {

    @Autowired
    DESService desService;

    @ApiOperation(value = "查员工",notes = "根据id查员工")
    @ApiImplicitParam(paramType = "path", name = "empno", value = "员工id",required = true, dataType = "Integer")
    @GetMapping(value = "/emp/find/{empno}")
    public Object findEmpById(@PathVariable("empno") Integer empno){
        return desService.findEmpById(empno);
    }

    @ApiOperation(value = "添加员工",notes = "新建员工信息添加")
    @GetMapping(value = "/emp/save")
    public Object addEmp(){

        Emp emp = new Emp();
        emp.setEmpno(8000);
        emp.setEname("Tony");
        emp.setSal(2000.0);

        return desService.saveEmp(emp)==true?"添加成功":"添加失败";
    }

    @ApiOperation(value = "改员工",notes = "根据sal、empno修改员工信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "sal", value = "员工工资",required = true, dataType = "Double"),
            @ApiImplicitParam(paramType = "path", name = "empno", value = "员工id",required = true, dataType = "Integer")
    })
    @GetMapping(value = "/emp/update/{sal}/{empno}")
    public Object updateEmpById(@PathVariable(value = "sal") double sal,
                            @PathVariable(value = "empno") Integer empno){

        return desService.updateEmpByEmpno(sal,empno)==1?"修改成功":"修改失败";

    }

    @ApiOperation(value = "删员工",notes = "根据empno删除员工")
    @ApiImplicitParam(paramType = "path", name = "empno", value = "员工id",required = true, dataType = "Integer")
    @GetMapping(value = "/emp/delete/{empno}")
    public Object deleteEmpById(@PathVariable(value = "empno") Integer empno){

        return desService.deleteEmpByEmpno(empno)==true?"删除失败":"删除成功";

    }

    @ApiOperation(value = "查员工、部门",notes = "根据sal查员工信息")
    @ApiImplicitParam(paramType = "path", name = "sal", value = "工资",required = true, dataType = "Double")
    @GetMapping(value = "/emp_dept/find/{sal}")
    public Object findEmpBySal(@PathVariable("sal") Double sal){

        return desService.findBySal(sal);

    }

    @ApiOperation(value = "查员工信息", notes = "根据id分页查询员工信息")
    @RequestMapping(value = "/emp/findAll")
    public Object findAll(){

        return desService.findAll();

    }

    @ApiOperation(value = "查部门信息", notes = "查询所有部门的详细信息和人数")
    @RequestMapping(value = "/dept/findDept")
    public Object findDept(){

        return desService.findDept();

    }


}

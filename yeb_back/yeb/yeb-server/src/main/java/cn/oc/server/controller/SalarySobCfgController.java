package cn.oc.server.controller;

import cn.oc.server.pojo.*;
import cn.oc.server.service.IEmployeeService;
import cn.oc.server.service.ISalaryAdjustService;
import cn.oc.server.service.ISalaryService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *员工账套
 * @ClassName : SalarySobCfgController
 * @Author: oc
 * @Date: 2022/05/21/16:11
 * @Description:
 **/
@RestController
@RequestMapping("/salary/sobCfg")
public class SalarySobCfgController {

    @Autowired
    private ISalaryService salaryService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ISalaryAdjustService salaryAdjustService;

    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/salaries")
    public List<Salary> getAllSalaries(){
        return salaryService.list();
    }

    @ApiOperation(value = "获取所有员工工资账套")
    @GetMapping("/")
    public RespPageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage ,
                                              @RequestParam(defaultValue = "10") Integer size){
        return employeeService.getEmployeeWithSalary(currentPage,size);
    }

    @ApiOperation(value = "更新员工账套")
    @PutMapping("/")
    public RespBean updateEmployeeSalary(Integer eid , Integer sid){
        SalaryAdjust salaryAdjust = new SalaryAdjust();
        Salary oldSalary = salaryService.getCountSalary(eid);
        int beforeSalary,afterSalary;
        if(employeeService.update(new UpdateWrapper<Employee>().set("salaryId",sid).eq("id",eid))){
            Salary newSalary = salaryService.getCountSalary(eid);
            beforeSalary = oldSalary.getAllSalary();
            afterSalary = newSalary.getAllSalary();
            salaryAdjust.setEid(eid).setBeforeSalary(beforeSalary).setAfterSalary(afterSalary)
                    .setAsDate(LocalDate.now()).setReason("部门调整").setRemark("部门调整");
            salaryAdjustService.save(salaryAdjust);
            return RespBean.success("更新成功");
        }else{
            return RespBean.error("更新失败");
        }
    }


}

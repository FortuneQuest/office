package cn.oc.server.controller;


import cn.oc.server.pojo.Department;
import cn.oc.server.pojo.RespBean;
import cn.oc.server.service.IDepartmentService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */


@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation("获取所有的部门")
    @GetMapping
    public List<Department> getAllDepartments() {
        return  departmentService.getAllDepartments();
    }

    @ApiOperation("添加部门")
    @PostMapping
    public RespBean addDepartment(@RequestBody Department department) {
        return departmentService.addDepartment(department);
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{id}")
    public RespBean deleteDepartment(@PathVariable Integer id) {
        return departmentService.deleteDepartment(id);
    }
}

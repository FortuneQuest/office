package cn.oc.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.oc.server.pojo.*;
import cn.oc.server.service.*;
import cn.oc.server.service.impl.NationServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
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
@RequestMapping("/employee/basic")
public class EmployeeController {


    @Autowired
    private IPoliticsStatusService politiService;

    @Autowired
    private IJoblevelService jobLevelService;

    @Autowired
    private INationService nationService;

    @Autowired
    private IPositionService positionService;

    @Autowired
     private IDepartmentService departmentService;

    @Autowired
    private IEmployeeService employeeService;


    @ApiOperation("查询所有员工(分页)")
    @GetMapping
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    Employee employee,
                                    LocalDate[] beginDateScope) {
        return employeeService.getEmployeeByPage(currentPage, size, employee, beginDateScope);
    }


    @ApiOperation("获取所有政治面貌")
    @GetMapping("/politicstatus")
    public List<PoliticsStatus> getAllPoliticStatus() {
        return politiService.list();
    }

    @ApiOperation("获取所有职称")
    @GetMapping("/joblevels")
    public List<Joblevel> getAllJobLevels() {
        return jobLevelService.list();
    }

    @ApiOperation("获取所有的民族")
    @GetMapping("/nations")
    public List<Nation> getAllNations() {
        return nationService.list();
    }

    @ApiOperation("获取所有的职位")
    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return  positionService.list();
    }

    @ApiOperation("获取所有的部门")
    @GetMapping("/deps")
    public List<Department> getAllDepartments() {
        return  departmentService.getAllDepartments();
    }

    @ApiOperation("获取工号")
    @GetMapping("/maxWorkID")
    public RespBean maxWorkID() {
        return employeeService.getMaxWorkID();
    }

    @ApiOperation("添加员工")
    @PostMapping
    public RespBean addEmp(@RequestBody Employee employee) {
        return employeeService.addEmp(employee);
    }

    @ApiOperation("更新员工")
    @PutMapping
    public RespBean updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateById(employee)) {
            return RespBean.success("更新成功");
        }
        return  RespBean.error("更新失败");
    }

    @ApiOperation("删除员工")
    @DeleteMapping("/{id}")
    public RespBean deleteEmp(@PathVariable Integer id) {
        if (employeeService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation("导出员工数据")
    @GetMapping(value="/export",produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response) {
        List<Employee> list = employeeService.getEmployee(null);
        ExportParams params = new ExportParams("员工表", "员工表", ExcelType.HSSF);
        Workbook WorkBook = ExcelExportUtil.exportExcel(params, Employee.class, list);
        ServletOutputStream out = null;
        try {
            //流形式传出
            response.setHeader("content-type", "application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("员工表.xls", "utf-8"));
            out = response.getOutputStream();
            WorkBook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @ApiOperation("导入员工数据")
    @PostMapping("/import")
    public RespBean importEmployee(MultipartFile file) {
        List<Nation> nationList = nationService.list();
        List<PoliticsStatus> politicsStatusList = politiService.list();
        List<Department> departmentList = departmentService.list();
        List<Joblevel> joblevelList = jobLevelService.list();
        List<Position> positionList = positionService.list();
        try {
            ImportParams params = new ImportParams();
            //去掉第一行标题
            params.setTitleRows(1);
            List<Employee> list = ExcelImportUtil.importExcel(file.getInputStream(),
                    Employee.class, params);
            list.forEach(employee -> {
                employee.setNationId(nationList.get(nationList.indexOf(new
                        Nation(employee.getNation().getName()))).getId());
                //政治面貌id
                employee.setPoliticId(politicsStatusList.get(politicsStatusList.indexOf(new
                        PoliticsStatus(employee.getPoliticsStatus().getName()))).getId());
                //部门id
                employee.setDepartmentId(departmentList.get(departmentList.indexOf(new
                        Department(employee.getDepartment().getName()))).getId());
                //职称id
                employee.setJobLevelId(joblevelList.get(joblevelList.indexOf(new
                        Joblevel(employee.getJoblevel().getName()))).getId());
                //职位id
                employee.setPosId(positionList.get(positionList.indexOf(new
                        Position(employee.getPosition().getName()))).getId());
            });
            if(employeeService.saveBatch(list)) {
                return RespBean.success("导入成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("导入失败");
        }
        return RespBean.error("导入失败!");
    }
}

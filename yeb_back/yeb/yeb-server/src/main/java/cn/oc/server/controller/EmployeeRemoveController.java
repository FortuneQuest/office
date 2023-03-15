package cn.oc.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.oc.server.pojo.Employee;
import cn.oc.server.pojo.EmployeeRemove;
import cn.oc.server.pojo.RespBean;
import cn.oc.server.service.IEmployeeRemoveService;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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
@RequestMapping("/employee-remove")
public class EmployeeRemoveController {

    @Autowired
    private IEmployeeRemoveService iEmployeeRemoveService;


    @ApiOperation("获取职位调动记录")
    @GetMapping("/")
    public void getEmployeeRemove() {
        iEmployeeRemoveService.list();
    }

    @ApiOperation("删除所有职位调动记录")
    @DeleteMapping("/")
    public RespBean deleteAllRemove() {
        List<Integer> removeList = iEmployeeRemoveService.getRemoveList();
        if (iEmployeeRemoveService.removeByIds(removeList)) {
            return   RespBean.success("删除成功");
        }else {
            return  RespBean.error("删除失败");
        }
    }

    @ApiOperation("导出职位调动数据")
    @GetMapping(value="/export",produces = "application/octet-stream")
    public void exportEmployeeRemove(HttpServletResponse response) {
        List<EmployeeRemove> list = iEmployeeRemoveService.getEmployeeRemove(null);
        ExportParams params = new ExportParams("职位调动", "职位调动", ExcelType.HSSF);
        Workbook WorkBook = ExcelExportUtil.exportExcel(params, EmployeeRemove.class, list);
        ServletOutputStream out = null;
        try {
            //流形式传出
            response.setHeader("content-type", "application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("职位调动记录.xls", "utf-8"));
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

}

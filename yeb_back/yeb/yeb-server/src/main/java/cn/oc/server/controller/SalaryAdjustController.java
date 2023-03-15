package cn.oc.server.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.oc.server.pojo.Employee;
import cn.oc.server.pojo.RespBean;
import cn.oc.server.pojo.RespPageBean;
import cn.oc.server.pojo.SalaryAdjust;
import cn.oc.server.service.ISalaryAdjustService;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/per/salary")
public class SalaryAdjustController {

    @Autowired
    private ISalaryAdjustService salaryAdjustService ;

    @ApiOperation(value = "获取调薪记录(分页)", notes = "获取调薪记录")
    @GetMapping("/")
    public RespPageBean getSalaryAdjust(@RequestParam(defaultValue = "1") Integer currentPage ,
                                        @RequestParam(defaultValue = "10") Integer size) {
        return salaryAdjustService.getSalaryAdjust(currentPage,size);
    }



    @ApiOperation(value = "删除调薪记录", notes = "删除调薪记录")
    @DeleteMapping("/")
    public RespBean deleteSalaryAdjust() {
        List<Integer> ids = salaryAdjustService.getIdsList();
        if (salaryAdjustService.removeByIds(ids)) {
            return RespBean.success("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }
    @ApiOperation("导出调薪记录")
    @GetMapping(value="/export",produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response) {
        List<SalaryAdjust> list = salaryAdjustService.getAdjust(null);
        ExportParams params = new ExportParams("调薪记录", "调薪记录", ExcelType.HSSF);
        Workbook WorkBook = ExcelExportUtil.exportExcel(params, SalaryAdjust.class, list);
        ServletOutputStream out = null;
        try {
            //流形式传出
            response.setHeader("content-type", "application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition",
                    "attachment;filename=" + URLEncoder.encode("调薪.xls", "utf-8"));
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

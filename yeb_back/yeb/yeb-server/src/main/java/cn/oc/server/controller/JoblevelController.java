package cn.oc.server.controller;


import cn.oc.server.mapper.JoblevelMapper;
import cn.oc.server.pojo.Joblevel;
import cn.oc.server.pojo.RespBean;
import cn.oc.server.service.IJoblevelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private IJoblevelService joblevelService;

    @ApiOperation("获取所有职称")
    @GetMapping
    public List<Joblevel> getAllJoblevels() {
        return   joblevelService.list();
    }


    @ApiOperation("添加职称")
    @PostMapping
    public RespBean addJoblevels(@RequestBody Joblevel joblevel) {
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)) {
            return    RespBean.success("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
    }

    @ApiOperation("更新职称")
    @PutMapping
    public RespBean updateJoblevel(@RequestBody Joblevel joblevel) {
        if (joblevelService.updateById(joblevel)) {
            return RespBean.success("数据更新成功");
        }else {
            return RespBean.error("数据更新失败");
        }
    }

    @ApiOperation("删除职称")
    @DeleteMapping("/{id}")
    public RespBean deleteJoblevel(@PathVariable Integer id) {
        if (joblevelService.removeById(id)) {
            return RespBean.error("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }

    @ApiOperation("批量删除")
    @DeleteMapping
    public RespBean deleteJoblevelsByIds(Integer[] ids) {
        if (joblevelService.removeByIds(Arrays.asList(ids))) {
            return RespBean.success("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }
}

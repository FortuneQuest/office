package cn.oc.server.controller;


import cn.oc.server.pojo.Menu;
import cn.oc.server.service.IAdminService;
import cn.oc.server.service.IMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @ApiOperation(value="通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenuByAdminId() {
        return menuService.getMenuByAdminId();
    }
}

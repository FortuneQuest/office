package cn.oc.server.controller;

import cn.oc.server.pojo.Menu;
import cn.oc.server.pojo.MenuRole;
import cn.oc.server.pojo.RespBean;
import cn.oc.server.pojo.Role;
import cn.oc.server.service.IMenuRoleService;
import cn.oc.server.service.IMenuService;
import cn.oc.server.service.IRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : PremissController
 * @Author: oc
 * @Date: 2022/03/07/9:09
 * @Description:
 **/
@RestController
@RequestMapping("/system/basic/permiss")
public class PremissController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IMenuRoleService menuRoleService;

    @ApiOperation("获取所有角色")
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation("添加角色")
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        if (role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("添加成功");
        } else {
            return RespBean.error("添加失败");
        }
    }


    @ApiOperation("删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid) {
        if (roleService.removeById(rid)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @ApiOperation("查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService. getAllMenus();
    }

    @ApiOperation("根据角色id查询菜单")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidById(@PathVariable Integer rid) {
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid",rid)).stream()
                .map(MenuRole::getMid).collect(Collectors.toList());
    }

    @ApiOperation("根据角色更新菜单")
    @PutMapping
    public RespBean updateMenuRole(Integer rid,Integer[] mids) {
        return menuRoleService.updateMenuRole(rid,mids);
    }
    }

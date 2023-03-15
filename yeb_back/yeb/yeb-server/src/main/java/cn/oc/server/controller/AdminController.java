package cn.oc.server.controller;


import cn.oc.server.pojo.Admin;
import cn.oc.server.pojo.AdminRole;
import cn.oc.server.pojo.RespBean;
import cn.oc.server.pojo.Role;
import cn.oc.server.service.IAdminRoleService;
import cn.oc.server.service.IAdminService;
import cn.oc.server.service.IRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IRoleService roleService;



    @ApiOperation("获取所有操作员")
    @GetMapping
    public List<Admin> getAllAdmin(String keyword) {
        return adminService.getAllAdmin(keyword);
    }

    @ApiOperation("更新操作员")
    @PutMapping
    public RespBean updateAdmin(@RequestBody Admin admin) {
        if (adminService.updateById(admin)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation("删除操作员")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id) {
        if (adminService.removeById(id)) {
            return RespBean.success("删除成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return  roleService.list();
    }

    @ApiOperation("更新操作员角色")
    @PutMapping("/role")
    public RespBean updateAdminRole(Integer adminId,Integer[] rids ) {
        return adminService.updateAdminRole(adminId, rids);
    }


}

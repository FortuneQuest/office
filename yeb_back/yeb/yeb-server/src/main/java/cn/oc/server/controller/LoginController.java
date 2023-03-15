package cn.oc.server.controller;

import cn.oc.server.pojo.Admin;
import cn.oc.server.pojo.AdminLoginParam;
import cn.oc.server.pojo.AdminRole;
import cn.oc.server.pojo.RespBean;
import cn.oc.server.service.IAdminRoleService;
import cn.oc.server.service.IAdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : LoginController
 * @Author: oc
 * @Date: 2022/03/02/13:21
 * @Description:
 **/
@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IAdminRoleService adminRoleService;

    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam ,HttpServletRequest request) {
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }


    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal) {
        if (null == principal) {
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }


    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.success("注销成功！");
    }

    @ApiOperation("注册新的操作员角色")
    @PostMapping("/register")
    public RespBean register(@RequestBody Admin admin){
        String password = admin.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        admin.setPassword(encoder.encode(password)).setEnabled(true);
        System.out.println(admin);
        if (adminService.save(admin)) {
            Admin admin1 = adminService.getOne(new QueryWrapper<Admin>().eq("name", admin.getName()));
            AdminRole adminRole = new AdminRole();
            adminRole.setAdminId(admin1.getId()).setRid(1);
            adminRoleService.save(adminRole);
            return RespBean.success("注册成功");
        }else{
            return RespBean.error("注册失败");
        }
    }

}

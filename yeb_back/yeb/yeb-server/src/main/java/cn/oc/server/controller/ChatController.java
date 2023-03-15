package cn.oc.server.controller;

import cn.oc.server.pojo.Admin;
import cn.oc.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : ChatController
 * @Author: oc
 * @Date: 2022/05/22/14:41
 * @Description:
 **/

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "获取所有操作人员")
    @GetMapping("/admin")
    public List<Admin> getAllAdmins (String keywords) {
        return adminService.getAllAdmin(keywords);
    }
}

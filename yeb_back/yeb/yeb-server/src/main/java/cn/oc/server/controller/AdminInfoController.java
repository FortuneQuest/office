package cn.oc.server.controller;

import cn.oc.server.pojo.Admin;
import cn.oc.server.pojo.RespBean;
import cn.oc.server.service.IAdminService;
import cn.oc.server.utils.FastDFSUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : AdminInfoController
 * @Author: oc
 * @Date: 2022/05/22/15:06
 * @Description:
 **/
@RestController
public class AdminInfoController {


    @Autowired
    private IAdminService adminService;

    @ApiOperation(value="更新当前用户信息")
    @PutMapping("/admin/info")
    public RespBean updateAdmin (@RequestBody Admin admin, Authentication authentication) {
        if (adminService.updateById(admin)) {
            SecurityContextHolder.getContext().setAuthentication(new
                    UsernamePasswordAuthenticationToken(admin,
                    null,authentication.getAuthorities()));
            return RespBean.success("更新成功");
        }else {
            return RespBean.error("更新失败");
        }
    }

    @ApiOperation(value = "更新用户密码")
    @PutMapping("/admin/pass")
    public RespBean updateAdminPassword(@RequestBody Map<String,Object>
                                                info){
        String oldPass = (String) info.get("oldPass");
        String pass = (String) info.get("pass");
        Integer adminId = (Integer) info.get("adminId");
        return adminService.updateAdminPassword(oldPass,pass,adminId);
    }

    @ApiOperation(value = "更新用户头像")
    @ApiImplicitParams({@ApiImplicitParam(name = "file", value = "头像", dataType = "MultipartFile")})
    @PostMapping("/admin/userface")
    public RespBean updateHrUserFace(MultipartFile file, Integer id, Authentication authentication) {
        //获取上传文件地址
        String[] fileAbsolutePath = FastDFSUtils.upload(file);
        String url = FastDFSUtils.getTrackerUrl() + fileAbsolutePath[0] + "/" +
                fileAbsolutePath[1];
        return adminService.updateAdminUserFace(url, id, authentication);
    }
}

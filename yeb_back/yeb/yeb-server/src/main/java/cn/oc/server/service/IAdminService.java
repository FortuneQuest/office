package cn.oc.server.service;

import cn.oc.server.pojo.Admin;
import cn.oc.server.pojo.Menu;
import cn.oc.server.pojo.RespBean;
import cn.oc.server.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 登录返回token
     * @param username
     * @param password
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     * @param username
     */
    Admin getAdminByUserName(String username);

    /**
     * 查询角色列表
     * @param id
     * @return
     */
    List<Role> getRoles(Integer id);

    /**
     * 获取所有的操作员
     * @param keyword
     * @return
     */
    List<Admin> getAllAdmin(String keyword);

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    /**
     * 更新用户名密码
     * @param oldPass
     * @param pass
     * @param adminId
     * @return
     */
    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);

    /**
     * 更新用户头像
     * @param url
     * @param id
     * @param authentication
     * @return
     */
    RespBean updateAdminUserFace(String url, Integer id, Authentication authentication);
}

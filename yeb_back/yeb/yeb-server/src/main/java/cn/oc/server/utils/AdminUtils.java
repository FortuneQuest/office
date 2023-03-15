package cn.oc.server.utils;

import cn.oc.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : AdminUtils
 * @Author: oc
 * @Date: 2022/03/07/14:16
 * @Description:
 **/
public class AdminUtils {

    /**
     * 获取当前登录操作员
     * @return
     */
    public static Admin getCurrentAdmin() {
        return (Admin)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}


package cn.oc.server.config.security.component;

import cn.oc.server.pojo.Menu;
import cn.oc.server.pojo.Role;
import cn.oc.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : CustomFilter
 * @Author: oc
 * @Date: 2022/03/06/19:50
 * @Description:  权限控制，根据url分析请求的角色
 **/
@Component
public class CustomFilter  implements FilterInvocationSecurityMetadataSource {


    @Autowired
    private IMenuService menuService;

     AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> menus = menuService.getMenusWithRole();
        for (Menu menu: menus
             ) {
            //判断请求url是否与菜单角色匹配
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                String[] str = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(str);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}

package cn.oc.server.service.impl;

import cn.oc.server.utils.AdminUtils;
import cn.oc.server.pojo.Menu;
import cn.oc.server.mapper.MenuMapper;
import cn.oc.server.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.baomidou.mybatisplus.core.toolkit.IdWorker.getId;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public List<Menu> getMenuByAdminId() {
        Integer adminId = AdminUtils.getCurrentAdmin().getId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        List menus = (List) valueOperations.get("menu" + adminId);
        //如果为空，去数据库获取
        if (CollectionUtils.isEmpty(menus)) {
            List<Menu> menu = menuMapper.getMenuByAdminId(adminId);
            //将数据设置到redie中
            valueOperations.set("menu"+adminId,menu);
        }

        return menus;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

}

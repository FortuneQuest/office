package cn.oc.server.mapper;

import cn.oc.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过id获取菜单信息
     * @param id
     * @return
     */
    List<Menu> getMenuByAdminId(Integer id);

    /**
     *
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 查询所有菜单
     * @return
     */
    List<Menu> getAllMenus();
}

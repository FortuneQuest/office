package cn.oc.server.service.impl;

import cn.oc.server.pojo.MenuRole;
import cn.oc.server.mapper.MenuRoleMapper;
import cn.oc.server.pojo.RespBean;
import cn.oc.server.service.IMenuRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    @Override
    @Transactional //事物的注解
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if (null == mids || 0 == mids.length) {
            return  RespBean.success("更新成功");
        }
        Integer result = menuRoleMapper.insertRecord(rid,mids);
        if (mids.length==result){
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}

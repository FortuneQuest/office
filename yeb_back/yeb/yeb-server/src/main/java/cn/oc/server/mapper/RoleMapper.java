package cn.oc.server.mapper;

import cn.oc.server.pojo.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    /**
     *
     * @return
     */
    List<Role> getRoles(Integer id);
}

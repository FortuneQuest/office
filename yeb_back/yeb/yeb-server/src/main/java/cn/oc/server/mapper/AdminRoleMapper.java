package cn.oc.server.mapper;

import cn.oc.server.pojo.AdminRole;
import cn.oc.server.pojo.RespBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 更新操作员的角色
     * @param adminId
     * @param rids
     * @return
     */
    Integer updateAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}

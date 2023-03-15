package cn.oc.server.mapper;

import cn.oc.server.pojo.Admin;
import cn.oc.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
public interface AdminMapper extends BaseMapper<Admin> {


    /**
     * 查找所有的操作员
     * @param id
     * @param keyword
     * @return
     */
    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keyword") String keyword);
}

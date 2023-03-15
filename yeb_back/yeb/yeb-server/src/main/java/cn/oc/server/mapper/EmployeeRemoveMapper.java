package cn.oc.server.mapper;

import cn.oc.server.pojo.EmployeeRemove;
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
public interface EmployeeRemoveMapper extends BaseMapper<EmployeeRemove> {

    List<Integer> getRemoveList();

    List<EmployeeRemove> getRemove(Integer id);
}

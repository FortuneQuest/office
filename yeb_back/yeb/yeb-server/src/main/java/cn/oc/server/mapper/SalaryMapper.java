package cn.oc.server.mapper;

import cn.oc.server.pojo.Salary;
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
public interface SalaryMapper extends BaseMapper<Salary> {
    Salary getCountSalary(@Param("eid") Integer eid);

}

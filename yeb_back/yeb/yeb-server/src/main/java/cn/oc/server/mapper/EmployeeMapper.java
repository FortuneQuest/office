package cn.oc.server.mapper;

import cn.oc.server.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 查询所有员工分页
     */

    IPage<Employee> getEmployeeByPage( Page<Employee> page, @Param("employee") Employee employee,
                                       @Param("beginDateScope") LocalDate[] beginDateScope);

    /**
     * 根据获取员工
     * @param id
     * @return
     */
    List<Employee> getEmployee(Integer id);

    /**
     * 获取所有工资账套
     * @param page
     *
     * @return
     */
    IPage<Employee> getEmployeeWithSalary(Page<Employee> page);

}

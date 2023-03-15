package cn.oc.server.service;

import cn.oc.server.pojo.Employee;
import cn.oc.server.pojo.RespBean;
import cn.oc.server.pojo.RespPageBean;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
public interface IEmployeeService extends IService<Employee> {

    /**
     * 查询员工分页
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployeeByPage(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * 获取最大的工号
     * @return
     */
    RespBean getMaxWorkID();

    /**
     * 添加员工
     * @param employee
     * @return
     */
    RespBean addEmp(Employee employee);

    /**
     *
     * @param id
     * @return
     */
    List<Employee> getEmployee(Integer id);

    /**
     * 获取所有工资账套
     * @param currentPage
     * @param size
     * @return
     */
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}

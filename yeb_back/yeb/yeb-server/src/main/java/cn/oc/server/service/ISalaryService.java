package cn.oc.server.service;

import cn.oc.server.pojo.Salary;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
public interface ISalaryService extends IService<Salary> {

    /**
     * 根据员工ID获取调薪前薪资账套
     * @param eid
     * @return
     */
    Salary getCountSalary(Integer eid);
}

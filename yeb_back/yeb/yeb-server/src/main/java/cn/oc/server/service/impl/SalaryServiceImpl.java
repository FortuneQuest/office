package cn.oc.server.service.impl;

import cn.oc.server.pojo.Salary;
import cn.oc.server.mapper.SalaryMapper;
import cn.oc.server.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {

    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public Salary getCountSalary(Integer eid) {
        return salaryMapper.getCountSalary(eid);
    }

}

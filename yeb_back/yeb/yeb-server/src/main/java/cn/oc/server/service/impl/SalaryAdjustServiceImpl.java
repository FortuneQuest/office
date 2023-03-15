package cn.oc.server.service.impl;

import cn.oc.server.pojo.Employee;
import cn.oc.server.pojo.RespPageBean;
import cn.oc.server.pojo.SalaryAdjust;
import cn.oc.server.mapper.SalaryAdjustMapper;
import cn.oc.server.service.ISalaryAdjustService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
@Service
public class SalaryAdjustServiceImpl extends ServiceImpl<SalaryAdjustMapper, SalaryAdjust> implements ISalaryAdjustService {

    @Autowired
    private SalaryAdjustMapper salaryAdjustMapper;

    @Override
    public RespPageBean getSalaryAdjust(Integer currentPage, Integer size) {
        Page<SalaryAdjust> page = new Page<>(currentPage,size);
        IPage<SalaryAdjust> salaryAdjustPage = salaryAdjustMapper.getSalaryAdjust(page);
        RespPageBean respPageBean = new RespPageBean(salaryAdjustPage.getTotal(),salaryAdjustPage.getRecords());
        return respPageBean;
    }

    @Override
    public List<Integer> getIdsList() {
        return salaryAdjustMapper.getIdsList();
    }

    @Override
    public List<SalaryAdjust> getAdjust(Integer id) {
        return salaryAdjustMapper.getAdjust(id);
    }
}

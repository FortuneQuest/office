package cn.oc.server.service.impl;

import cn.oc.server.pojo.EmployeeRemove;
import cn.oc.server.mapper.EmployeeRemoveMapper;
import cn.oc.server.service.IEmployeeRemoveService;
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
public class EmployeeRemoveServiceImpl extends ServiceImpl<EmployeeRemoveMapper, EmployeeRemove> implements IEmployeeRemoveService {

    @Autowired
    private EmployeeRemoveMapper removeMapper;

    @Override
    public List<Integer> getRemoveList() {
        return   removeMapper.getRemoveList();
    }

    @Override
    public List<EmployeeRemove> getEmployeeRemove(Integer id) {
        return removeMapper.getRemove(id);
    }
}

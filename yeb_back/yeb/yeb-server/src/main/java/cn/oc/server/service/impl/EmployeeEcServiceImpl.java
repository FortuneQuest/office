package cn.oc.server.service.impl;

import cn.oc.server.mapper.EmployeeMapper;
import cn.oc.server.pojo.Employee;
import cn.oc.server.pojo.EmployeeEc;
import cn.oc.server.mapper.EmployeeEcMapper;
import cn.oc.server.pojo.RespBean;
import cn.oc.server.pojo.RespPageBean;
import cn.oc.server.service.IEmployeeEcService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
@Service
public class EmployeeEcServiceImpl extends ServiceImpl<EmployeeEcMapper, EmployeeEc> implements IEmployeeEcService {


}

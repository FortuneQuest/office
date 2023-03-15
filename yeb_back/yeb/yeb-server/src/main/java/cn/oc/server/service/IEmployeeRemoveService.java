package cn.oc.server.service;

import cn.oc.server.pojo.EmployeeRemove;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
public interface IEmployeeRemoveService extends IService<EmployeeRemove> {

    List<Integer> getRemoveList();

    List<EmployeeRemove> getEmployeeRemove(Integer id);
}

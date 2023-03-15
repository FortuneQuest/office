package cn.oc.server.service;

import cn.oc.server.pojo.RespPageBean;
import cn.oc.server.pojo.SalaryAdjust;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
public interface ISalaryAdjustService extends IService<SalaryAdjust> {

    RespPageBean getSalaryAdjust(Integer currentPage, Integer size);

    List<Integer> getIdsList();

    List<SalaryAdjust> getAdjust(Integer id);
}

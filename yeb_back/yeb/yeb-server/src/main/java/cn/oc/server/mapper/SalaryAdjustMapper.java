package cn.oc.server.mapper;

import cn.oc.server.pojo.SalaryAdjust;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
public interface SalaryAdjustMapper extends BaseMapper<SalaryAdjust> {


    IPage<SalaryAdjust> getSalaryAdjust(Page<SalaryAdjust> page);

    List<Integer> getIdsList();

    List<SalaryAdjust> getAdjust(Integer id);
}

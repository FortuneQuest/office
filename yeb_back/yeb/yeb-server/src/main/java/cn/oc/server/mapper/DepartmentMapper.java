package cn.oc.server.mapper;

import cn.oc.server.pojo.Department;
import cn.oc.server.pojo.RespBean;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有部门
     * @return
     * @param parentId
     */
    List<Department> getAllDepartments(Integer parentId);

    /**
     * 添加部门
     * @param department
     * @return
     */
    RespBean addDepartment(Department department);

    /**
     * 删除部门
     * @param department
     */
    void deleteDepartment(Department department);
}

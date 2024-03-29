package cn.oc.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_employee_remove")
@ApiModel(value="EmployeeRemove对象", description="")
public class EmployeeRemove implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Excel(name = "员工编号")
    @ApiModelProperty(value = "员工id")
    private Integer eid;

    @Excel(name ="调动后部门")
    @ApiModelProperty(value = "调动后部门")
    private Integer afterDepId;

    @Excel(name = "调动后职位")
    @ApiModelProperty(value = "调动后职位")
    private Integer afterJobId;

    @Excel(name = "调动日期")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @ApiModelProperty(value = "调动日期")
    private LocalDate removeDate;

    @Excel(name = "调动原因",width=40)
    @ApiModelProperty(value = "调动原因")
    private String reason;

    @Excel( name = "备注",width=40)
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "name")
    @Excel(name = "员工名字")
    @TableField(exist = false)
    private String name;
}

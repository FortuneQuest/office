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
@TableName("t_salary_adjust")
@ApiModel(value="SalaryAdjust对象", description="")
public class SalaryAdjust implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Excel(name = "员工编号")
    @ApiModelProperty(value = "员工ID")
    private Integer eid;

    @Excel(name = "调薪日期",width=20,format="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Shanghai")
    @ApiModelProperty(value = "调薪日期")
    private LocalDate asDate;

    @Excel(name = "调前薪资")
    @ApiModelProperty(value = "调前薪资")
    private Integer beforeSalary;

    @Excel(name = "调后薪资")
    @ApiModelProperty(value = "调后薪资")
    private Integer afterSalary;

    @Excel(name="调薪原因")
    @ApiModelProperty(value = "调薪原因")
    private String reason;

    @Excel(name="备注",width=40)
    @ApiModelProperty(value = "备注")
    private String remark;

    @Excel(name="姓名")
    @ApiModelProperty(value = "员工姓名")
    @TableField(exist = false)
    private String name;

}

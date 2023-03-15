package cn.oc.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : RespPageBean
 * @Author: oc
 * @Date: 2022/03/07/20:15
 * @Description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {

    /**
     * 总条数
     */
    private Long total;

    /**
     * 数据list
     */
    private List<?> data;

}

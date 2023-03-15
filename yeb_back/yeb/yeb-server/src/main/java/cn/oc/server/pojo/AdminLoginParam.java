package cn.oc.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : AdminLogin
 * @Author: oc
 * @Date: 2022/03/02/13:06
 * @Description: 用户登录实体类
 * 使用chain属性，setter方法返回当前对象 即返回的是AdminLoginParam对象
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AdminLogin对象", discriminator = "用户登录实体类")
public class AdminLoginParam {

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "验证码", required = true)
    private String code;
}

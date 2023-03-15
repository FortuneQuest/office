package cn.oc.server.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : ChatMsg
 * @Author: oc
 * @Date: 2022/05/22/14:31
 * @Description:
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ChatMsg {

    private String from;
    private String to;
    private String content;
    private LocalDateTime date;
    private String formNickName;
}

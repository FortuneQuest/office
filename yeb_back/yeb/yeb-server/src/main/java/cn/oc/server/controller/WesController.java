package cn.oc.server.controller;

import cn.oc.server.pojo.Admin;
import cn.oc.server.pojo.ChatMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : WesController
 * @Author: oc
 * @Date: 2022/05/22/14:34
 * @Description:
 **/
@RestController
public class WesController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/ws/chat")
    public void handleMessage (Authentication authentication, ChatMsg chatMsg) {
        Admin admin = (Admin) authentication.getPrincipal();
        chatMsg.setFrom(admin.getUsername());
        chatMsg.setFormNickName(admin.getName());
        chatMsg.setDate(LocalDateTime.now());
        messagingTemplate.convertAndSendToUser(chatMsg.getTo(),"/queue/chat", chatMsg);
    }

}

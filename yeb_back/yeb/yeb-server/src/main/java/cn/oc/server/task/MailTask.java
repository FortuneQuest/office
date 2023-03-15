package cn.oc.server.task;

import cn.oc.server.pojo.Employee;
import cn.oc.server.pojo.MailConstants;
import cn.oc.server.pojo.MailLog;
import cn.oc.server.service.IEmployeeService;
import cn.oc.server.service.IMailLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : MailTask
 * @Author: oc
 * @Date: 2022/03/09/17:10
 * @Description: 邮件发送定时任务
 **/
@Component
public class MailTask {

    @Autowired
    private IMailLogService mailLogService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 邮件发送任务
     * 10秒执行一次
     */
    @Scheduled(cron = "0/1 * * * * ?")
    public void mailTask() {
        List<MailLog> list = mailLogService.list(new QueryWrapper<MailLog>().eq("status", 0).
                lt("tryTime",
                        LocalDateTime.now()));
        list.forEach(mailLog -> {
            //如果重复次数超过三次，更新状态为投递失败不在尝试
            if (3 <= mailLog.getCount()) {
                mailLogService.update(new UpdateWrapper<MailLog>().set("status", 2)
                        .eq("msgId", mailLog.getMsgId()));
            }
            mailLogService.update(new UpdateWrapper<MailLog>().set("count", mailLog.getCount() + 1)
                    .set("updateTime", LocalDateTime.now())
                            .set("tryTime", LocalDateTime.now().plusMinutes(MailConstants.MSG_OUT))
                                .eq("msgId", mailLog.getMsgId()));
            Employee emp = employeeService.getEmployee(mailLog.getEid()).get(0);
            //发送邮件
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME, MailConstants.MAIL_ROUTING_KEY_NAME,
                    emp,new CorrelationData(mailLog.getMsgId()));
        });
    }
}

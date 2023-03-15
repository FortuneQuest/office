package cn.oc.mail;

import cn.oc.server.pojo.Employee;
import cn.oc.server.pojo.MailConstants;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : MailReceiver
 * @Author: oc
 * @Date: 2022/03/09/12:25
 * @Description:
 **/
@Component
public class MailReceiver {
    private static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 邮件发送
     */
    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message message, Channel channel) {
        Employee employee = (Employee) message.getPayload();
        MessageHeaders headers = message.getHeaders();
        long tag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);
        String msgId = (String) headers.get("spring_returned_message_correlation");
        HashOperations hashOperations = redisTemplate.opsForHash();


        try {
            if(hashOperations.entries("mail_log").containsKey(msgId)){
                logger.error("消息已经被消费了======>{}",msgId);
                /**手动确认消息
                 * tag:消息的标签
                 * multiple:是否批量确认
                 */
                channel.basicAck(tag,false);
                return;
            }

            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg);
            //发件人
            helper.setFrom(mailProperties.getUsername());
            //收件人
            helper.setTo(employee.getEmail());
            //主题
            helper.setSubject("入职欢迎邮件");
            //发送日期
            helper.setSentDate(new Date());
            //邮件内容
            Context context= new Context();
            context.setVariable("name",employee.getName());
            context.setVariable("posName",employee.getPosition().getName());
            context.setVariable("joblevelName",employee.getJoblevel().getName());
            context.setVariable("departmentName",employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail,true);
            //发送邮件
            javaMailSender.send(msg);
            LOGGER.info("邮件发送成功");
            //将消息id存入redis
            hashOperations.put("mail_log",msgId,"ok");
            //发送成功，手动确认消息
            channel.basicAck(tag,false);
        } catch (Exception e) {
            /**
             * tag:消息的标签
             * multiple:是否批量确认
             * requeue:是否重新入队
             */
            try {
                channel.basicNack(tag,false,true);
            } catch (IOException ex) {
                logger.error("邮件发送失败=====>{}", e.getMessage());
            }
            logger.error("邮件发送失败=====>{}", e.getMessage());

        }
    }

}

package cn.oc.server.config;

import cn.oc.server.pojo.MailConstants;
import cn.oc.server.pojo.MailLog;
import cn.oc.server.service.IMailLogService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : RabbitMQConfig
 * @Author: oc
 * @Date: 2022/03/09/16:13
 * @Description:
 **/
@Configuration
public class RabbitMQConfig {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;

    @Autowired
    private IMailLogService mailLogService;

    @Bean
    public RabbitTemplate rabbitTemplate() {

        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
        /**
         * 消息确认回调，确认是否回到broker
         * data: 消息体 唯一标识
         * ack: 是否确认 确认结果
         * cause: 异常信息
         */
        template.setConfirmCallback((correlationData, ack, cause) -> {
            String msgId = correlationData.getId();
            if (ack) {
                logger .info("{}=====>消息发送成功",msgId);
                mailLogService.update(new UpdateWrapper<MailLog>().set("status", 1).eq("msg_id", msgId));
            } else {
                logger .info("{}=====>消息发送失败",msgId);
            }
        } );

        /**
         * 消息被拒绝回调
         * message: 拒绝原因
         * replyCode: 响应码
         * replyText: 响应消息
         * exchange: 交换机
         * routingKey: 路由键
         */
        template.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            logger .info("{}=====>消息发送queue时失败",message.getBody());
        } );
        return template;
    }




    @Bean
    public Queue queue(){
        return new Queue(MailConstants.MAIL_QUEUE_NAME);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(MailConstants.MAIL_EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with(MailConstants.MAIL_ROUTING_KEY_NAME);
    }

}

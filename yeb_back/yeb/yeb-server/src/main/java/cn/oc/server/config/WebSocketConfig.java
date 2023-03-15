package cn.oc.server.config;

import cn.oc.server.config.security.component.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : WebSocketConfig
 * @Author: oc
 * @Date: 2022/05/21/19:15
 * @Description:
 **/
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 添加Endpoint，这样再网页可以通过websocket连接上服务器
     * 也就是我们配置websocket的服务地址，并且可以指定是否使用socketJs
     *
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /**
         * 将wes/ep 注册一个Stomp的节点（endpoint）,用户链接整个端点就可以使用websocket通讯,并指定使用SockJS协议。
         * setAllowedOrigins("*")允许所有的域名链接上我们的服务器。
         * withSockJS()指定使用SockJS协议。
         */
        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").withSockJS();
    }

    /**
     * 配置通道参数配置
     * @param registration
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor  = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                //判断是否为链接，如果是，获取token并设置到用户对象
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String token = accessor.getFirstNativeHeader("Auth-Token");
                    if (!StringUtils.isEmpty(token)) {
                        String authToken = token.substring(tokenHead.length());
                        String username = jwtTokenUtil.getUserNameFormToken(authToken);
                        //Token中存在用户名
                        if (!StringUtils.isEmpty(username)) {
                            //登录用户
                            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                            //验证token是否有效,并设置到用户对象
                            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
                                        null, userDetails.getAuthorities());
                                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                accessor.setUser(authenticationToken);
                            }
                        }
                    }
                }
                return message;
            }
        });
    }

    /**
     * 配置消息代理
     *
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //配置消息代理,并且可以配置多个不同的消息代理,配置代理目的地前缀为/queue，可以在客户端通过/queue/消息代理目的地前缀来推送接收消息
        registry.enableSimpleBroker("/queue");
    }
}

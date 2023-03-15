package cn.oc.server.service.impl;

import cn.oc.server.pojo.MailLog;
import cn.oc.server.mapper.MailLogMapper;
import cn.oc.server.service.IMailLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author oc
 * @since 2022-03-02
 */
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}

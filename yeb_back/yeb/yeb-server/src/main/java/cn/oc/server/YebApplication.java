package cn.oc.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName : yebApplication
 * @Author: oc
 * @Date: 2022/03/01/21:52
 * @Description:
 **/
@MapperScan("cn.oc.server.mapper")
@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class YebApplication {
    public static void main(String[] args) {
        SpringApplication.run(YebApplication.class);
    }
}

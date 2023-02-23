package cn.nesc.general.authcenter;


import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by summer on 2017/12/25.
 */
//@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"cn.nesc.general"}, exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@EnableEurekaClient
@EnableFeignClients(basePackages = {"cn.nesc.general"})
@EnableCircuitBreaker
@EnableCaching
@EnableTransactionManagement
@EnableApolloConfig
@Slf4j
@Controller
public class AuthcenterApplication
{
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args)
    {
        SpringApplication.run(AuthcenterApplication.class, args);
    }

    @RequestMapping("shutdown")
    public String stopApp()
    {
        SpringApplication.exit(applicationContext);
        return "stopped ...";
    }

}

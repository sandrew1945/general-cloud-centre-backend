package cn.nesc.general.gateway;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = {"cn.nesc.general.stub.authcenter"})
@EnableZuulProxy
@EnableCaching
@EnableApolloConfig
@EnableEurekaClient
public class ZuulServerApplication
{
    private static Logger logger = LoggerFactory.getLogger(ZuulServerApplication.class);


    public static void main(String[] args)
    {
        SpringApplication.run(ZuulServerApplication.class, args);
        logger.info("Gateway started!");
        //        new SpringApplicationBuilder(EurekaServerApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

}
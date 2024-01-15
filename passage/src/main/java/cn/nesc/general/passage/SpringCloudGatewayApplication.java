package cn.nesc.general.passage;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = {"cn.nesc.general"}, exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
//@EnableFeignClients(basePackages = {"cn.nesc.general.stub.authcenter"})
@EnableDiscoveryClient
@EnableCaching
@EnableApolloConfig
@EnableEurekaClient
public class SpringCloudGatewayApplication
{
    private static Logger logger = LoggerFactory.getLogger(SpringCloudGatewayApplication.class);


    public static void main(String[] args)
    {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
        logger.info("Gateway started!");
    }

}
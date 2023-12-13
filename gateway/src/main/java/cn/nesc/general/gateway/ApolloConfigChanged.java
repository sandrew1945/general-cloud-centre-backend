package cn.nesc.general.gateway;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName ApolloConfigChanged
 * @Description
 * @Author summer
 * @Date 2022/2/18 14:04
 **/
@Component
public class ApolloConfigChanged implements ApplicationContextAware
{
    private static Logger logger = LoggerFactory.getLogger(ZuulServerApplication.class);

    private ApplicationContext applicationContext;

    @ApolloConfigChangeListener
    private void someChangeHandler(ConfigChangeEvent changeEvent)
    {
        for (String key : changeEvent.changedKeys())
        {
            ConfigChange change = changeEvent.getChange(key);
            logger.info("Found change - {}", change.toString());
        }

        // 更新相应的bean的属性值，主要是存在@ConfigurationProperties注解的bean
        this.applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
    {
        this.applicationContext = applicationContext;
    }
}
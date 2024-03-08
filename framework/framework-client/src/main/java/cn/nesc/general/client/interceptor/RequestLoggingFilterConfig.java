package cn.nesc.general.client.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * @ClassName RequestLoggingFilterConfig
 * @Description
 * @Author summer
 * @Date 2024/3/8 15:56
 **/
@Configuration
public class RequestLoggingFilterConfig
{
    @Bean
    public CommonsRequestLoggingFilter logFilter()
    {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }
}

package cn.nesc.general.client.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author summer
 * @Description 通过Feign调用时在header中添加token
 * @Date 15:57 2023/3/13
 * @Param 
 * @return 
 **/
@Slf4j
@Component
public class FeignRequestInterceptor implements RequestInterceptor
{
    @Resource
    HttpServletRequest request;

    @Override
    public void apply(RequestTemplate requestTemplate)
    {
        String token = request.getHeader("sid");
        if (StringUtils.isNotEmpty(token))
        {
            requestTemplate.header("sid", token);
        }
    }
}

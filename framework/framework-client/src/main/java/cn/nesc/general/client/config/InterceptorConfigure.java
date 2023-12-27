package cn.nesc.general.client.config;

import cn.nesc.general.client.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @ClassName InterceptorConfigure
 * @Description
 * @Author summer
 * @Date 2023/3/7 14:53
 **/
@EnableWebMvc
@Configuration
public class InterceptorConfigure implements WebMvcConfigurer
{
    @Resource
    private SecurityInterceptor securityInterceptor;

    public static final String[] excludeUrls = { "/login" };

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludeUrls)
                .order(1);
    }
}

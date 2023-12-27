package cn.nesc.general.client.interceptor;

import cn.nesc.general.core.bean.AclUserBean;
import cn.nesc.general.core.util.ContextHolder;
import cn.nesc.general.stub.authcenter.AuthCenterStub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author summer
 * @Description 请求用户信息处理Interceptor
 * @Date 15:21 2023/3/7
 * @Param
 * @return
 **/
@Slf4j
@Component
public class SecurityInterceptor implements HandlerInterceptor
{

    @Resource
    @Lazy
    private AuthCenterStub authCenterStub;

    /**
     * @Author summer
     * @Description 处理请求前，从认证中心获取登录用户信息，存入ThreadLocal
     *              以解决各微服务中无法获取当前登录用户信息的问题
     * @Date 14:18 2023/3/13
     * @Param [request, response, handler]
     * @return boolean
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        try
        {
            log.debug("Loading current thread contextHolder, Request token is " + request.getHeader("sid"));
            AclUserBean loginUser = authCenterStub.getLoginUser();
//            JsonNode jsonNode = authCenterStub.getLoginUser();
//            AclUserBean loginUser = JsonUtil.jsonObject2JavaObject(jsonNode, AclUserBean.class);
            String sid = request.getHeader("sid");
            ContextHolder.set("sid", sid);
            ContextHolder.set(sid, loginUser);
            return true;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new Exception(e);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {
        try
        {
            ContextHolder.remove();
            log.debug("Current thread contextHolder is clear");
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}

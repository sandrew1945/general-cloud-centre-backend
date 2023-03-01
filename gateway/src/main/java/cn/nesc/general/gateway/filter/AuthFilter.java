/**
 * Copyright (C), 2015-2022, 东北证券股份有限公司
 * FileName: AuthFilter
 * Author:   summer
 * Date:     2022/2/9 14:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.general.gateway.filter;

import cn.nesc.general.common.nosql.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @ClassName AuthFilter
 * @Description
 * @Author summer
 * @Date 2022/2/9 14:09
 **/
@Component
public class AuthFilter extends ZuulFilter
{
    private Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    @Value("${gateway.manager.whitelist}")
    private Set<String> whiteList;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。
     * 这里定义为pre，代表会在请求被路由之前执行。
     *
     * @return
     */
    @Override
    public String filterType()
    {
        return PRE_TYPE;
    }

    /**
     * filter执行顺序，通过数字指定。
     * 数字越大，优先级越低。
     *
     * @return
     */
    @Override
    public int filterOrder()
    {
        return 0;
    }

    /**
     * 判断该过滤器是否需要被执行。这里我们直接返回了true，因此该过滤器对所有请求都会生效。
     * 实际运用中我们可以利用该函数来指定过滤器的有效范围。
     *
     * @return
     */
    @Override
    public boolean shouldFilter()
    {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        // 白名单不进行验证
        if (whiteList.contains(request.getRequestURI()))
        {
            return false;
        }
        return true;
    }

    /**
     * 过滤器的具体逻辑
     *
     * @return
     */
    @Override
    public Object run() throws ZuulException
    {
        logger.debug("validate token ....");

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String token = request.getHeader("sid");
        if (token == null || token.isEmpty())
        {
            unAuthResponse(ctx, "您无权限访问系统, Token不能为空");
        }
        boolean isValidate = redisUtil.exists(token);
        logger.debug("isValidate  ------->" + isValidate);
        if (!isValidate)
        {
            unAuthResponse(ctx, "您无权限访问系统, Token无效或已过期");
        }
        return null;
    }

    /**
     * @return void
     * @Author summer
     * @Description 返回未认证response
     * @Date 16:24 2022/2/11
     * @Param [ctx]
     **/
    private void unAuthResponse(RequestContext ctx, String msg)
    {
        ctx.getResponse().addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(401);
        ctx.setResponseBody("{\"code\":\"401\",\"msg\":\"" + msg + "\",\"data\":null}");
    }
}

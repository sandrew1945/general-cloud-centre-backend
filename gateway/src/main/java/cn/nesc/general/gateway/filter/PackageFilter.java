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

import cn.nesc.general.core.result.JsonResult;
import cn.nesc.toolkit.common.json.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.http.HttpStatus.*;

/**
 * @ClassName AuthFilter
 * @Description
 * @Author summer
 * @Date 2022/2/9 14:09
 **/
@Component
public class PackageFilter extends ZuulFilter
{
    private Logger logger = LoggerFactory.getLogger(PackageFilter.class);

    @Value("${gateway.response.package.switch}")
    private boolean bodyPackage;

    /**
     * 过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。
     * 这里定义为post，代表会在请求被路由之后执行。
     *
     * @return
     */
    @Override
    public String filterType()
    {
        return POST_TYPE;
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
        return 10;
    }

    /**
     * 根据服务返回的header信息判断，服务如果异常会在header里添加自定义属性 Has-Error=error
     *
     * @return
     */
    @Override
    public boolean shouldFilter()
    {
        // response的content-type如果为application/octet-stream则不拦截
        RequestContext ctx = RequestContext.getCurrentContext();
        if (302 == ctx.getResponse().getStatus())
        {
            return false;
        }
        else if (ctx.getOriginResponseHeaders().stream().filter(entity -> "Content-Type".equalsIgnoreCase(entity.first())).anyMatch(entity -> entity.second().startsWith("application/octet-stream")))
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
        logger.debug("package the result ....");
        RequestContext ctx = RequestContext.getCurrentContext();
        logger.debug("Current requestURI is:" + ctx.getRequest().getRequestURI());
        try (final InputStream responseDataStream = ctx.getResponseDataStream())
        {
            if (ctx.getThrowable() != null)
            {
                // 处理controller抛出异常的情况
                JsonResult result = new JsonResult();
                result.requestFailure(StringUtils.isEmpty(ctx.getThrowable().getMessage()) ? "Internal Server Error" : ctx.getThrowable().getMessage());
                ctx.setResponseStatusCode(OK.value());
                ctx.setResponseBody(JsonUtil.javaObject2String(result));
            }
            else
            {
                // 处理controller未抛出异常的情况
                int statusCode = ctx.getResponseStatusCode();
                if (responseDataStream == null)
                {
                    logger.debug("BODY: {}", "null");
                    return null;
                }
                String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
                logger.debug("BODY: {}", responseData);
                if (OK.value() == statusCode)
                {
                    // 请求正常
                    if (!bodyPackage)
                    {
                        // 根据设置不需要对结果进行包装
                        return null;
                    }
                    // 验证返回结果是否为json格式，如果controller返回为对象则会序列化为json格式，如果返回基本类型咋不会被序列化为json格式
                    Object obj = JsonUtil.validateJsonStr(responseData) ? JsonUtil.string2JavaObject(responseData, Object.class) : responseData;
                    // 变成JsonResult格式
                    JsonResult result = new JsonResult();
                    result.requestSuccess(obj);
                    ctx.setResponseBody(JsonUtil.javaObject2String(result));
                }
                else if (NOT_FOUND.value() == statusCode)
                {
                    // 请求异常
                    // 变成JsonResult格式
                    JsonResult result = new JsonResult();
                    result.requestFailure("Path " + ctx.getRequest().getRequestURI() + " Not Found");
                    ctx.setResponseStatusCode(OK.value());
                    ctx.setResponseBody(JsonUtil.javaObject2String(result));
                }
                else if (FORBIDDEN.value() == statusCode)
                {
                    // 请求异常
                    // 变成JsonResult格式
                    JsonResult result = new JsonResult();
                    result.requestFailure("Path " + ctx.getRequest().getRequestURI() + " is Forbidden");
                    ctx.setResponseStatusCode(OK.value());
                    ctx.setResponseBody(JsonUtil.javaObject2String(result));
                }
                else if (BAD_REQUEST.value() == statusCode)
                {
                    // 请求异常
                    // 变成JsonResult格式
                    JsonResult result = new JsonResult();
                    result.requestFailure("Path " + ctx.getRequest().getRequestURI() + " is Bad Request");
                    ctx.setResponseStatusCode(OK.value());
                    ctx.setResponseBody(JsonUtil.javaObject2String(result));
                }
                else
                {
                    // 请求异常
                    if (JsonUtil.validateJsonStr(responseData))
                    {
                        // 变成JsonResult格式
                        JsonNode node = JsonUtil.string2JsonObject(responseData);
                        JsonResult result = new JsonResult();
                        result.requestFailure(node.get("message").asText("Internal Server Error"));
                        ctx.setResponseStatusCode(OK.value());
                        ctx.setResponseBody(JsonUtil.javaObject2String(result));
                    }
                    else
                    {
                        ctx.setResponseBody(responseData);
                    }
                }
            }
        }
        catch (Exception e)
        {
            throw new ZuulException(e, INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
        return null;
    }

}

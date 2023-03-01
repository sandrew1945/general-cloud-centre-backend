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

import cn.nesc.general.common.utils.JsonUtil;
import cn.nesc.general.core.result.JsonResult;
import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

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
     * 这里定义为pre，代表会在请求被路由之前执行。
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
        return 1;
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
        if (!bodyPackage)
        {
            return null;
        }
        logger.debug("package the result ....");
        RequestContext ctx = RequestContext.getCurrentContext();
        try (final InputStream responseDataStream = ctx.getResponseDataStream()) {

            if(responseDataStream == null) {
                logger.debug("BODY: {}", "");
                return null;
            }

            String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
            logger.debug("BODY: {}", responseData);
            // 变成JsonResult格式
            Object obj = JsonUtil.string2JavaObject(responseData, Object.class);
            JsonResult result = new JsonResult();
            result.requestSuccess(obj);
            ctx.setResponseBody(JsonUtil.javaObject2String(result));
//            StringBuilder packagedBody = new StringBuilder("{");
//            packagedBody.append("\"result\": true,");
//            packagedBody.append("\"msg\": \"\",");
//            packagedBody.append("\"data\": ").append(responseData);
//            packagedBody.append("}");
        }
        catch (Exception e) {
            throw new ZuulException(e, INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
        return null;
    }

}

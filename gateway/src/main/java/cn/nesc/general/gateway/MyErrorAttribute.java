package cn.nesc.general.gateway;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MyErrorAttribute
 * @Description 自定义的错误属性
 * @Author summer
 * @Date 2022/2/14 16:47
 **/
@Component
public class MyErrorAttribute extends DefaultErrorAttributes
{
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options)
    {
        Map<String, Object> result = super.getErrorAttributes(webRequest, options);
        // 自定义限流的错误消息
        if (!result.get("status").equals(429))
        {
            return result;
        }
        //修改返回状态码为200
        webRequest.setAttribute("javax.servlet.error.status_code", 200, RequestAttributes.SCOPE_REQUEST);
        //自定义消息体 需要返回一个map，如果是实体类，可以转为map再返回
        Map<String, Object> map = new HashMap<>();
        map.put("result", false);
        map.put("msg", "请勿频繁请求.");
        return map;
    }
}
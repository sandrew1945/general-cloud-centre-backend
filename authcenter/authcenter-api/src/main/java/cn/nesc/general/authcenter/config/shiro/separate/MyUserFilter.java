package cn.nesc.general.authcenter.config.shiro.separate;

import cn.nesc.general.core.result.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by summer on 2019/12/19.
 */
public class MyUserFilter extends UserFilter
{
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        // 这里也可以不用保存 保存当前request 可在登陆后重新请求当前 request
        this.saveRequest(request);
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.setContentType("application/json;charset=utf-8");
        JsonResult result = new JsonResult();
        result.requestFailure("The user is not logged in");
        ObjectMapper objectMapper = new ObjectMapper();
        httpResponse.getWriter().write(objectMapper.writeValueAsString(result));
        return false;
    }
}

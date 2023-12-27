package cn.nesc.general.client.controller;

import cn.nesc.general.core.bean.AclUserBean;
import cn.nesc.general.core.util.ContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ClientBaseController
 * @Description 微服务客户端BaseController
 *              获取当前用户信息为从认证中心获取后存入ThreadLocal
 * @Author summer
 * @Date 2023/3/13 16:04
 **/
@Slf4j
public class ClientBaseController
{
    protected final static String LOGIN_USER = "loginUser";
    protected final static String APP_KEY = "authc.appKey";

    @Resource
    protected HttpServletRequest request;


    @InitBinder
    protected void ininBinder(WebDataBinder binder)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    protected AclUserBean getLoginUser()
    {
        String token = request.getHeader("sid");
        AclUserBean loginUser = ContextHolder.get(token, AclUserBean.class);
        return loginUser;
    }

    protected void setloginUser(AclUserBean loginUser)
    {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(LOGIN_USER, loginUser);
    }
}

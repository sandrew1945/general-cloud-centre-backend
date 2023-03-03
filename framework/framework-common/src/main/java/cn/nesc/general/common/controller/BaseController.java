package cn.nesc.general.common.controller;


import cn.nesc.general.common.bean.AclUserBean;
import cn.nesc.general.core.exception.BaseException;
import cn.nesc.general.core.result.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestControllerAdvice
public class BaseController
{
    private final static String LOGIN_USER = "loginUser";
    private final static String APP_KEY = "authc.appKey";

    private final static  String ERROR_TAG = "Has-Error";

    /**
     *  处理数据绑定异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = ServletRequestBindingException.class)
    public JsonResult errorBindingHandler(HttpServletResponse response, Exception ex) {
        // 服务发生异常，在response的header中添加自定义头信息 Has-Error=true，方便其他服务判断是否请求成功
        response.setHeader(ERROR_TAG, "true");
        JsonResult result = new JsonResult();
        result.requestFailure(ex.getMessage());
        return result;
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public JsonResult noAuthorization(HttpServletResponse response, Exception ex) {
        response.setHeader(ERROR_TAG, "true");
        JsonResult result = new JsonResult();
        result.requestFailure("您无权访问该资源，请联系系统管理员");
        return result;
    }

    /**
     *  处理请求动作异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public JsonResult errorActionHandler(HttpServletResponse response, Exception ex) {
        response.setHeader(ERROR_TAG, "true");
        JsonResult result = new JsonResult();
        while (null != ex)
        {
            Throwable throwable = ex.getCause();
            if (null == throwable)
            {
                break;
            }
            ex = (Exception) throwable;
        }
        result.requestFailure(ex.getMessage());
        return result;
    }

    /**
     *  未知异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public JsonResult unknowExceptionHandler(HttpServletResponse response, Exception ex) {
        response.setHeader(ERROR_TAG, "true");
        JsonResult result = new JsonResult();
        result.requestFailure(ex.getMessage());
        return result;
    }

    @InitBinder
    protected void ininBinder(WebDataBinder binder)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    /**
     * Function    : 获取登录用户信息
     * LastUpdate  : 2014年5月20日
     *
     * @return
     */
    protected AclUserBean getLoginUser()
    {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        AclUserBean loginUser = (AclUserBean) session.getAttribute(LOGIN_USER);
        return loginUser;
    }

    /**
     * Function    : 设置用户登录信息
     * LastUpdate  : 2014年5月20日
     *
     * @param loginUser
     */
    protected void setloginUser(AclUserBean loginUser)
    {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(LOGIN_USER, loginUser);
    }

    /**
     * 获取系统的appKey
     *
     * @return
     */
    protected String getAppKey()
    {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return (String) session.getAttribute(APP_KEY);
    }

}
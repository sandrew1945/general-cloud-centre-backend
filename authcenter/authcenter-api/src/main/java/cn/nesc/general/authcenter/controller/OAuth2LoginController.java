package cn.nesc.general.authcenter.controller;


import cn.nesc.general.authcenter.bean.login.LoginBO;
import cn.nesc.general.authcenter.bean.login.LoginConvertor;
import cn.nesc.general.authcenter.config.shiro.oauth2.OAuth2Token;
import cn.nesc.general.authcenter.service.LoginService;
import cn.nesc.general.common.controller.BaseController;
import cn.nesc.general.core.exception.JsonException;
import cn.nesc.general.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
@Slf4j
public class OAuth2LoginController extends BaseController
{

    @Resource
    private LoginService loginService;

    @Resource
    private LoginConvertor loginConvertor;

    @Value(value = "${auth.custom.test}")
    private String custom;

    @GetMapping(value = "/oauth2/sso")
    public void login(String code, String state, HttpServletResponse res) throws JsonException
    {
        try
        {
            OAuth2Token token = new OAuth2Token(code);
            LoginBO loginBO = loginService.login(token);
            log.debug("=======================" + loginBO.getToken());

            Cookie coocie= new Cookie("quasar_template_token", loginBO.getToken());
            coocie.setPath("/");
            res.addCookie(coocie);
            String redirectURL = "http://10.6.33.61:83/#" + state;
            res.sendRedirect(redirectURL);
        }
        catch (Exception e)
        {
            String errorMsg = null;
            if (e instanceof ServiceException)
            {
                errorMsg = "用户名或密码错误";
            }
            else
            {
                errorMsg = "用户登录失败";
            }
            log.error(e.getMessage(), e);
            throw new JsonException(errorMsg, e);
        }
    }


}

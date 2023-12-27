package cn.nesc.general.authcenter.controller;


import cn.nesc.general.authcenter.bean.login.LoginConvertor;
import cn.nesc.general.authcenter.bean.login.LoginVO;
import cn.nesc.general.authcenter.bean.usermanager.UserInfoVO;
import cn.nesc.general.authcenter.service.LoginService;
import cn.nesc.general.authcenter.service.util.MenuNode;
import cn.nesc.general.core.exception.JsonException;
import cn.nesc.general.core.exception.ServiceException;
import cn.nesc.general.core.shiro.MyUsernamePasswordToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Slf4j
public class LoginController extends BaseController
{

    @Resource
    private LoginService loginService;

    @Resource
    private LoginConvertor loginConvertor;

    @Value(value = "${auth.custom.test}")
    private String custom;

    @PostMapping(value = "/login")
    public LoginVO login(String userCode, String password) throws JsonException
    {
        log.debug("custom ----------->" + custom);
        try
        {
            MyUsernamePasswordToken token = new MyUsernamePasswordToken();
            token.setUsername(userCode);
            token.setPassword(password.toCharArray());
            return loginConvertor.toLoginVO(loginService.login(token));
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

    @GetMapping(value = "/userInfo")
    public UserInfoVO userInfo() throws JsonException
    {
        try
        {
            return loginService.userInfo(getLoginUser());
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("获取用户信息失败", e);
        }
    }

    @GetMapping(value = "/validateToken")
    public boolean validateToken(String token) throws JsonException
    {
        try
        {
            log.debug("token -------->" + token);
            return loginService.validateSession(token);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("验证登录状态失败", e);
        }
    }

    @GetMapping(value = "/getMenuByRole")
    public List<MenuNode> getMenuByRole(Integer roleId) throws JsonException
    {
        try
        {
            return loginService.getMenuByRole(roleId);
        }
        catch (ServiceException e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("获取系统菜单失败", e);
        }
    }

    @PostMapping(value = "/logout")
    public
    @ResponseBody
    boolean logout() throws JsonException
    {
        try
        {
            loginService.logout();
            return true;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("登出系统失败", e);
        }

    }

}

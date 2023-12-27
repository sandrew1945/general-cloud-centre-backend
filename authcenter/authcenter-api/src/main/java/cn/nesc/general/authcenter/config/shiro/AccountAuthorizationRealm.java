package cn.nesc.general.authcenter.config.shiro;

import cn.nesc.general.authcenter.config.shiro.oauth2.IDAASAccessToken;
import cn.nesc.general.authcenter.config.shiro.oauth2.IDAASUserInfo;
import cn.nesc.general.authcenter.config.shiro.oauth2.OAuth2Token;
import cn.nesc.general.authcenter.model.TmUserPO;
import cn.nesc.general.authcenter.service.UserManagerService;
import cn.nesc.general.core.bean.AclUserBean;
import cn.nesc.general.core.shiro.Principal;
import cn.nesc.toolkit.common.httpclient.HttpClientUtil;
import cn.nesc.toolkit.common.httpclient.HttpResponse;
import cn.nesc.toolkit.common.json.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ThreadContext;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;


/**
 * 管理员的认证,角色,权限控制
 */
@Log4j2
public class AccountAuthorizationRealm extends AuthorizingRealm
{
    @Resource
    @Lazy
    UserManagerService userManagerService;

    /**
     * 查询获得用户信息
     * AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）
     * <p>
     * AuthenticationInfo有两个作用：
     * 1、如果Realm 是AuthenticatingRealm 子类，则提供给AuthenticatingRealm 内部使用的
     * CredentialsMatcher进行凭据验证；（如果没有继承它需要在自己的Realm中自己实现验证）；
     * 2、提供给SecurityManager来创建Subject（提供身份信息）；
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
    {
        //HealthcareUsernamePasswordToken token = (HealthcareUsernamePasswordToken) authcToken;
        try
        {
            AclUserBean loginUser = new AclUserBean();
            ThreadContext.put("loginUser", loginUser);
            if (authcToken instanceof UsernamePasswordToken)
            {
                // 登录页面登录
                // 通过token获取登录账号并查询数据库获取用户信息存入AuthenticationInfo
                /*这里编写认证代码*/
                UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
                if (token.getUsername() == null)
                {
                    throw new AccountException("用户名密码不正确");
                }
                TmUserPO user = userManagerService.getUserByCode(token.getUsername());
                if (user != null)
                {
                    Principal principal = new Principal();
                    principal.setName(user.getUserCode());
                    loginUser.setUserCode(user.getUserCode());
                    loginUser.setUserName(user.getUserName());
                    return new SimpleAuthenticationInfo(principal, user.getPassword(), getName());
                }
                else
                {
                    return null;
                }
            }
            else if (authcToken instanceof OAuth2Token)
            {
                // OAuth2登录
                // 通过code换取accessToken，再通过accessToken查询用户信息存入AuthenticationInfo
                OAuth2Token token = (OAuth2Token) authcToken;
                String code = token.getCode();
                log.debug("code ========> " + code);
                HttpClientUtil clientUtil = new HttpClientUtil();
                // 获取AccessToken
                String getAccessTokenUrl = "http://10.211.96.22:38776/oauth/token?grant_type=authorization_code&code=" + code + "&client_id=711005e1cb10c3bed67bd90a199066e2in0wythefO9&client_secret=9r8k0NyGKWMSrNpkkdVsLzIX2cMq47vhlpaIdYjENP&redirect_uri=http%3A%2F%2F10.6.33.61%3A9080%2Fapi%2Fauth%2Foauth2%2Fsso";
                HttpResponse accessTokenRes = clientUtil.sendHttpPost(getAccessTokenUrl, null);
                if (200 == accessTokenRes.getReturnCode())
                {
                    IDAASAccessToken accessToken = JsonUtil.string2JavaObject(accessTokenRes.getReturnContent(), IDAASAccessToken.class);
                    log.debug("access token ========> " + accessToken.getAccessToken());
                    // 获取userInfo
                    String getUserInfoUrl = "http://10.211.96.22:38776/api/bff/v1.2/oauth2/userinfo?access_token=" + accessToken.getAccessToken();
                    HttpResponse userInfoRes = clientUtil.sendHttpGet(getUserInfoUrl, null);
                    if (200 == userInfoRes.getReturnCode())
                    {
                        JsonNode userInfoNode = JsonUtil.string2JsonObject(userInfoRes.getReturnContent()).get("data");
                        IDAASUserInfo userInfo = JsonUtil.jsonObject2JavaObject(userInfoNode, IDAASUserInfo.class);
                        log.debug("usercode ==========>" + userInfo.getUsername());
                        log.debug("username ==========>" + userInfo.getNickname());
                        loginUser.setUserCode(userInfo.getUsername());
                        loginUser.setUserName(userInfo.getNickname());
                        if (null != userInfo)
                        {
                            Principal principal = new Principal();
                            principal.setName(userInfo.getUsername());
                            return new SimpleAuthenticationInfo(principal, accessToken.getAccessToken(), getName());
                        }
                        else
                        {
                            return null;
                        }
                    }
                }
            }
            return null;
        }
        catch (Exception e)
        {
            throw new AuthenticationException("用户认证失败", e);
        }
    }

    /**
     * 表示根据用户身份获取授权信息
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.在配有缓存的情况下，只加载一次.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection)
    {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();
        roles.add("ACTUATOR");
        authorizationInfo.setRoles(roles);
        return authorizationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token)
    {
        return token != null && token instanceof AuthenticationToken;
    }

}

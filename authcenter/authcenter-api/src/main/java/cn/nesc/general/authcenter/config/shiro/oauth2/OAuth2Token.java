package cn.nesc.general.authcenter.config.shiro.oauth2;

import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * @ClassName OAuth2Token
 * @Description
 * @Author summer
 * @Date 2023/11/23 16:14
 **/
public class OAuth2Token implements HostAuthenticationToken, RememberMeAuthenticationToken
{
    private String code;
    private boolean rememberMe;
    private String host;

    public OAuth2Token(String code)
    {
        this.code = code;
    }

    public OAuth2Token(String code, boolean rememberMe)
    {
        this.code = code;
        this.rememberMe = rememberMe;
    }

    public OAuth2Token(String code, String host)
    {
        this.code = code;
        this.host = host;
    }

    public OAuth2Token(String code, boolean rememberMe, String host)
    {
        this.code = code;
        this.rememberMe = rememberMe;
        this.host = host;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    @Override
    public String getHost()
    {
        return this.host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    @Override
    public boolean isRememberMe()
    {
        return this.rememberMe;
    }

    public void setRememberMe(boolean rememberMe)
    {
        this.rememberMe = rememberMe;
    }

    @Override
    public Object getPrincipal()
    {
        return this.getCode();
    }

    @Override
    public Object getCredentials()
    {
        return null;
    }
}

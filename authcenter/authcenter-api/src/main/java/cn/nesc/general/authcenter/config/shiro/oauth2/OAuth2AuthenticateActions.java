package cn.nesc.general.authcenter.config.shiro.oauth2;

/**
 * @ClassName OAuth2AuthenticateActions
 * @Description
 * @Author summer
 * @Date 2023/11/27 15:17
 **/
public interface OAuth2AuthenticateActions
{
    OAuth2AuthenticateActions getAccessToken();


    IDAASUserInfo getUserInfo();

}

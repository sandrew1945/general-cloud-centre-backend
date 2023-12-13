package cn.nesc.general.authcenter.config.shiro.oauth2;

import cn.nesc.general.common.utils.ObjectUtils;
import cn.nesc.toolkit.common.exception.HttpClientException;
import cn.nesc.toolkit.common.httpclient.HttpClientUtil;
import cn.nesc.toolkit.common.httpclient.HttpResponse;
import cn.nesc.toolkit.common.json.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName IDAASAuthenticateProcess
 * @Description 使用IDAAS平台处理OAuth2认证
 * @Author summer
 * @Date 2023/11/27 15:30
 **/
@Slf4j
public class IDAASAuthenticateProcess
{
    /**
     * @return cn.nesc.general.authcenter.config.shiro.oauth2.OAuth2AuthenticateActions
     * @Author summer
     * @Description
     * @Date 16:11 2023/11/27
     * @Param [code]
     **/
    public OAuth2AuthenticateActions perform(String code)
    {
        HttpClientUtil clientUtil = new HttpClientUtil();
        final IDAASAccessToken accessToken = new IDAASAccessToken();
//        IDAASUserInfo userInfo = new IDAASUserInfo();

        OAuth2AuthenticateActions actions = new OAuth2AuthenticateActions()
        {
            @Override
            public OAuth2AuthenticateActions getAccessToken()
            {
                try
                {
                    String getAccessTokenUrl = "http://10.211.96.22:38776/oauth/token?grant_type=authorization_code&code=" + code + "&client_id=711005e1cb10c3bed67bd90a199066e2in0wythefO9&client_secret=9r8k0NyGKWMSrNpkkdVsLzIX2cMq47vhlpaIdYjENP&redirect_uri=http%3A%2F%2F10.6.33.61%3A9080%2Fapi%2Fauth%2Foauth2%2Fsso";
                    HttpResponse accessTokenRes = clientUtil.sendHttpPost(getAccessTokenUrl, null);
                    if (200 == accessTokenRes.getReturnCode())
                    {
                        IDAASAccessToken token = JsonUtil.string2JavaObject(accessTokenRes.getReturnContent(), IDAASAccessToken.class);
                        ObjectUtils.copyProperties(accessTokenRes, token);
                        log.debug("access token ========> " + accessToken.getAccessToken());
                    }
                }
                catch (HttpClientException e)
                {
                    throw new RuntimeException(e);
                }
                return this;
            }

            @Override
            public IDAASUserInfo getUserInfo()
            {
                IDAASUserInfo userInfo = null;
                try
                {
                    // 获取userInfo
                    String getUserInfoUrl = "http://10.211.96.22:38776/api/bff/v1.2/oauth2/userinfo?access_token=" + accessToken.getAccessToken();
                    HttpResponse userInfoRes = clientUtil.sendHttpGet(getUserInfoUrl, null);
                    if (200 == userInfoRes.getReturnCode())
                    {
                        JsonNode userInfoNode = JsonUtil.string2JsonObject(userInfoRes.getReturnContent()).get("data");
                        userInfo = JsonUtil.jsonObject2JavaObject(userInfoNode, IDAASUserInfo.class);
                        log.debug("usercode ==========>" + userInfo.getUsername());
                        log.debug("username ==========>" + userInfo.getNickname());
                    }
                }
                catch (HttpClientException e)
                {
                    throw new RuntimeException(e);
                }
                return userInfo;
            }
        };
        return actions;
    }
}

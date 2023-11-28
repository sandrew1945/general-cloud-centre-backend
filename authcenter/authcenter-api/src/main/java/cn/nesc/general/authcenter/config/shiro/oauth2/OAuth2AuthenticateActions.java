/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: OAuth2AuthenticateActions
 * Author:   summer
 * Date:     2023/11/27 15:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

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

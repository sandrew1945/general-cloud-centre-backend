/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: IDAASAccessToken
 * Author:   summer
 * Date:     2023/11/24 14:32
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.general.authcenter.config.shiro.oauth2;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @ClassName IDAASAccessToken
 * @Description
 * @Author summer
 * @Date 2023/11/24 14:32
 **/
@Data
public class IDAASAccessToken
{
    @JsonAlias("access_token")
    private String accessToken;
    @JsonAlias("token_type")
    private String tokenType;
    @JsonAlias("refresh_token")
    private String refreshToken;
    @JsonAlias("expires_in")
    private String expiresIn;
    private String scope;
    private String jti;


}

/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: IDAASUserInfo
 * Author:   summer
 * Date:     2023/11/24 14:34
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.general.authcenter.config.shiro.oauth2;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * @ClassName IDAASUserInfo
 * @Description
 * @Author summer
 * @Date 2023/11/24 14:34
 **/
@Data
public class IDAASUserInfo
{
    private String sub;
    private String ouid;
    private String nickname;
    @JsonAlias("phone_number")
    private String phoneNumber;
    @JsonAlias("ou_name")
    private String ouName;
    private String email;
    private String username;
}

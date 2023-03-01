/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: LoginBO
 * Author:   summer
 * Date:     2023/3/1 13:52
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.general.authcenter.bean.login;

import lombok.Data;

/**
 * @ClassName LoginBO
 * @Description
 * @Author summer
 * @Date 2023/3/1 13:52
 **/
@Data
public class LoginBO
{
    private Integer userId;

    private String token;
}

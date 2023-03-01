/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: UserManagerBO
 * Author:   summer
 * Date:     2023/2/27 14:19
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.general.authcenter.bean.usermanager;

import lombok.Data;

/**
 * @ClassName UserManagerBO
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:19
 **/
@Data
public class UserManagerBO
{
    private Integer userId;

    private String userCode;

    private String userName;

    private String roleName;

    private String roleCode;

    private Integer sex;

    private String phone;

    private String mobile;

    private String email;

    private Integer userStatus;
}

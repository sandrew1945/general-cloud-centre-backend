/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: UserManagerDTO
 * Author:   summer
 * Date:     2023/2/24 16:09
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.general.authcenter.bean.usermanager;

import cn.nesc.general.common.bean.DTO;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserManagerDTO
 * @Description
 * @Author summer
 * @Date 2023/2/24 16:09
 **/
@Data
public class UserManagerDTO implements DTO
{
    private Integer userId;

    private String userCode;

    private String userName;

    private String password;

    private Integer sex;

    private String phone;

    private String mobile;

    private String email;

    private Date birthday;

    private String avatar;

    private Integer userType;

    private Integer userStatus;
}

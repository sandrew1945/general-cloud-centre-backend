/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: UserPageQueryVO
 * Author:   summer
 * Date:     2023/2/27 14:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.general.authcenter.bean.usermanager;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName UserPageQueryVO
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:17
 **/
@Data
@Builder
public class UserPageQueryVO
{
    private Integer userId;

    private String userCode;

    private String userName;

    private String roleName;

    private String roleCode;

    private String sex;

    private String phone;

    private String mobile;

    private String email;

    private String userStatus;
}

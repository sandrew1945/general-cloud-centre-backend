/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: RoleManagerConvertor
 * Author:   summer
 * Date:     2023/2/27 14:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.general.authcenter.bean.login;

import cn.nesc.general.authcenter.bean.usermanager.UserInfoVO;
import cn.nesc.general.authcenter.bean.usermanager.UserManagerBO;
import cn.nesc.general.common.bean.AclUserBean;
import org.mapstruct.Mapper;

/**
 * @ClassName RoleManagerConvertor
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:25
 **/
@Mapper(componentModel = "spring")
public interface LoginConvertor
{
    LoginBO toLoginBO(AclUserBean aclUserBean);

    LoginVO toLoginVO(LoginBO loginBO);

    UserInfoVO toUserInfoVO(UserManagerBO userManagerBO);

}

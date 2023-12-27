package cn.nesc.general.authcenter.bean.login;

import cn.nesc.general.authcenter.bean.usermanager.UserInfoVO;
import cn.nesc.general.authcenter.bean.usermanager.UserManagerBO;
import cn.nesc.general.core.bean.AclUserBean;
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

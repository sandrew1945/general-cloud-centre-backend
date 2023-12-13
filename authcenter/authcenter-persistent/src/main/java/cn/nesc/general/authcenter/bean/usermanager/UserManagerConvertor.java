package cn.nesc.general.authcenter.bean.usermanager;

import cn.nesc.general.authcenter.model.TmUserPO;
import cn.nesc.general.authcenter.model.TmUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @ClassName RoleManagerConvertor
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:25
 **/
@Mapper(componentModel = "spring")
public interface UserManagerConvertor
{
    @Mapping(source = "userManagerBO.sex", target = "sex")
    @Mapping(source = "userManagerBO.userStatus", target = "userStatus")
    UserPageQueryVO toUserPageQueryVO(UserManagerBO userManagerBO);

    @Mapping(source = "userManagerBO.sex", target = "sex")
    @Mapping(source = "userManagerBO.userStatus", target = "userStatus")
    @Mapping(source = "userManagerBO.birthday", target = "birthday", dateFormat = "yyyy/MMM/dd")
    TmUserVO toUserVO(UserManagerBO userManagerBO);

    UserManagerBO toUserManagerBO(TmUserPO tmUserPO);

    TmUserPO toUserPO(UserManagerDTO userManagerDTO);


    List<UserPageQueryVO> toUserPageQueryVO(List<UserManagerBO> userManagerBOList);
}

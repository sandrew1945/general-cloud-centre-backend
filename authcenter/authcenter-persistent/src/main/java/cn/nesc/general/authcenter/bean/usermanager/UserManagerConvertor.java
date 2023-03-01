/**
 * Copyright (C), 2015-2023, 东北证券股份有限公司
 * FileName: UserManagerConvertor
 * Author:   summer
 * Date:     2023/2/27 14:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 **/

package cn.nesc.general.authcenter.bean.usermanager;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @ClassName UserManagerConvertor
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


    List<UserPageQueryVO> toUserPageQueryVO(List<UserManagerBO> doctor);
}

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

import cn.nesc.general.authcenter.model.TmRoleVO;
import cn.nesc.general.common.bean.VO;
import cn.nesc.general.common.dictionary.Fixcode;
import cn.nesc.general.core.annotation.EnumHandler;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UserManagerBO
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:19
 **/
@Data
public class UserInfoVO implements VO
{
    private Integer userId;

    private String userCode;

    private String userName;

    private String roleName;

    private String roleCode;

    private Date birthday;

    private String avatar;

    @EnumHandler(Fixcode.class)
    private String sex;

    private String phone;

    private String mobile;

    private String email;

    private List<TmRoleVO> roleList;

    @EnumHandler(Fixcode.class)
    private String userStatus;
}

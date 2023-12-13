package cn.nesc.general.authcenter.bean.usermanager;

import cn.nesc.general.common.bean.VO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserPageQueryVO
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:17
 **/
@Data
@Builder
public class UserPageQueryVO implements VO
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

    private Date birthday;

    private String userStatus;
}

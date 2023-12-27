package cn.nesc.general.authcenter.bean.usermanager;

import cn.nesc.general.core.bean.BO;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName UserManagerBO
 * @Description
 * @Author summer
 * @Date 2023/2/27 14:19
 **/
@Data
public class UserManagerBO implements BO
{
    private Integer userId;

    private String userCode;

    private String userName;

    private String roleName;

    private String roleCode;

    private Date birthday;

    private String avatar;

    private Integer sex;

    private String phone;

    private String mobile;

    private String email;

    private Integer userStatus;
}

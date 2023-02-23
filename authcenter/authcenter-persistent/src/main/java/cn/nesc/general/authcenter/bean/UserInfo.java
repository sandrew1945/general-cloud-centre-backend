package cn.nesc.general.authcenter.bean;

import com.sandrew.bury.bean.PO;
import lombok.Data;

@Data
public class UserInfo extends PO
{
    private Integer userId;

    private String userCode;

    private String userName;

    private Integer roleId;

    private String roleName;

    private String roleCode;

    private Integer sex;

    private String phone;

    private String mobile;

    private String email;

    private String avatarPath;

}

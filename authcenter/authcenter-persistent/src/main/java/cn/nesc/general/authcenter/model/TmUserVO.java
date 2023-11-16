package cn.nesc.general.authcenter.model;

import cn.nesc.general.common.bean.VO;
import lombok.Data;

@Data
public class TmUserVO implements VO
{

    private Integer userId;
    private String userCode;
    private String userName;
    private String sex;
    private String phone;
    private String mobile;
    private String email;
    private String birthday;
    private String avatar;
    private String userType;
    private String userStatus;

}
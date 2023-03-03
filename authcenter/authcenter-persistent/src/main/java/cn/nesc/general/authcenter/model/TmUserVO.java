package cn.nesc.general.authcenter.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TmUserVO implements Serializable {

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
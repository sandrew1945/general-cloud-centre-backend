package cn.nesc.general.authcenter.bean;

import lombok.Data;

import java.util.Date;

/**
 *
 */
@Data
public class RoleBean
{
    private Integer roleId;

    private String roleCode;

    private String roleName;

    private Integer roleType;

    private Integer roleStatus;

    private Integer isDelete;

    private Integer applicationId;

    private String applicationName;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;
}

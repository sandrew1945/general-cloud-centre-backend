package cn.nesc.general.authcenter.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TmRoleVO implements Serializable {

    private Integer roleId;

    private String roleCode;

    private String roleName;

    private String roleType;

    private String roleStatus;

    private static final long serialVersionUID = 1L;

}
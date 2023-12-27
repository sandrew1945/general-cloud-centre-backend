package cn.nesc.general.authcenter.dictionary;

import cn.nesc.general.core.enums.BaseEnum;

/**
 * Created by summer on 2018/7/3.
 */
public enum Fixcode implements BaseEnum
{
    STATUS_ENABLE(1001, 10011001, "有效"),
    STATUS_DISABLE(1001, 10011002, "无效"),
    SEX_MALE(1002, 10021001, "男"),
    SEX_FEMALE(1002, 10021002, "女"),
    SEX_UNKNOW(1002, 10021003, "未知"),
    IF_TYPE_YES(1003, 10031001, "是"),
    IF_TYPE_NO(1003, 10031002, "否"),
    ACCESS_TYPE_LOGIN(1004, 10041001, "登录"),
    ACCESS_TYPE_LOGOUT(1004, 10041002, "登出"),

    /*  员工状态  */
    STAFF_STATUS_JOB(2001, 20011001, "在职"),
    STAFF_STATUS_DIMISSION(2001, 20011002, "离职"),
    STAFF_STATUS_RETIRE(2001, 20011003, "退休"),
    STAFF_STATUS_RECUPERATE(2001, 20011004, "离岗退养");



    public Integer type;       // 字典类别
    public Integer fixcode;     // 字典码
    public String desc;        // 描述
    private Fixcode(Integer type, Integer fixcode, String desc)
    {
        this.type = type;
        this.fixcode = fixcode;
        this.desc = desc;
    }

    public Integer getType()
    {
        return type;
    }

    public Integer getCode()
    {
        return fixcode;
    }

    public String getDesc()
    {
        return desc;
    }
}
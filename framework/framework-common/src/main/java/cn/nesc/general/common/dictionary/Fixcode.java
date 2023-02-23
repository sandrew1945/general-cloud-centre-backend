package cn.nesc.general.common.dictionary;

/**
 * Created by summer on 2018/7/3.
 */
public enum Fixcode
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


    STAFF_STATUS_JOB(2001, 20011001, "在职"),
    STAFF_STATUS_DIMISSION(2001, 20011002, "离职"),
    STAFF_STATUS_RETIRE(2001, 20011003, "退休"),
    STAFF_STATUS_RECUPERATE(2001, 20011004, "离岗退养");



    private Integer type;       // 字典类别
    private Integer fixcode;     // 字典码
    private String desc;        // 描述
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

    public Integer getFixcode()
    {
        return fixcode;
    }

    public String getDesc()
    {
        return desc;
    }
}
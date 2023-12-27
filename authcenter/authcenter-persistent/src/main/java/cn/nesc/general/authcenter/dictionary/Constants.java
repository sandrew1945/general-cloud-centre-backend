package cn.nesc.general.authcenter.dictionary;

public interface Constants
{
	public static String LOGIN_USER = "loginUser"; // 登录用户
	public static String APP_KEY = "authc.appKey"; // appKey


	public static Integer STATUS_ENABLE = new Integer("10011001"); //有效
	public static Integer STATUS_DISABLE = new Integer("10011002"); //无效
	public static Integer SEX_MALE = new Integer("10021001"); //男
	public static Integer SEX_FEMALE = new Integer("10021002"); //女
	public static Integer SEX_UNKNOW = new Integer("10021003"); //未知
	public static Integer IF_TYPE_YES = new Integer("10031001"); //是
	public static Integer IF_TYPE_NO = new Integer("10031002"); //否
	public static Integer ACCESS_TYPE_LOGIN = new Integer("10041001"); //登录
	public static Integer ACCESS_TYPE_LOGOUT = new Integer("10041002"); //登出


	public static Integer STAFF_STATUS_JOB = new Integer("20011001"); //在职
	public static Integer STAFF_STATUS_DIMISSION = new Integer("20011002"); //离职
	public static Integer STAFF_STATUS_RETIRE = new Integer("20011003"); //退休
	public static Integer STAFF_STATUS_RECUPERATE = new Integer("20011004"); //离岗退养

}

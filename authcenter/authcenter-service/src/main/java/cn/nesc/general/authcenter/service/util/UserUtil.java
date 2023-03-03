/**********************************************************************
* <pre>
* FILE : UserUtil.java
* CLASS : UserUtil
*
* AUTHOR : Administrator
*
* FUNCTION : TODO
*
*
*======================================================================
* CHANGE HISTORY LOG
*----------------------------------------------------------------------
* MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
*----------------------------------------------------------------------
* 		  |2014年6月4日| Administrator| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: UserUtil.java,v 0.1 2014年6月4日 下午3:54:05 Administrator Exp $
*/

package cn.nesc.general.authcenter.service.util;


import cn.nesc.general.authcenter.model.TmUserPO;
import cn.nesc.general.common.bean.AclUserBean;

/**
 * Function    : 针对用户新的的工具类，适用于TmUserPO及ACLUserBean
 * @author     : Administrator
 * CreateDate  : 2014年6月4日
 * @version    :
 */
public class UserUtil
{
	/**
	 * 
	 * Function    : 判断用户是否有用户名、密码信息
	 * LastUpdate  : 2014年6月4日
	 * @param user
	 * @return
	 */
	public static boolean isVerificationUser(TmUserPO user)
	{
		if (null == user)
		{
			return false;
		}
		else if ((null == user.getUserCode() || "".equals(user.getUserCode())) && (null == user.getPassword() || "".equals(user.getPassword())))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * 
	 * Function    : 判断用户是否已经选择完角色
	 * LastUpdate  : 2014年6月4日
	 * @param user
	 * @return
	 */
	public static boolean hasRole(AclUserBean user)
	{
		if (null == user)
		{
			return false;
		}
		else if (null == user.getRoleId())
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}

/**********************************************************************
* <pre>
* FILE : StesCredentialsMatcher.java
* CLASS : StesCredentialsMatcher
*
* AUTHOR : SuMMeR
*
* FUNCTION : TODO
*
*
*======================================================================
* CHANGE HISTORY LOG
*----------------------------------------------------------------------
* MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
*----------------------------------------------------------------------
* 		  |2016年9月21日| SuMMeR| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: StesCredentialsMatcher.java,v 0.1 2016年9月21日 下午1:34:08 SuMMeR Exp $
*/

package cn.nesc.general.authcenter.config.shiro;

import cn.nesc.general.core.bean.Principal;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;


/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2016年9月21日
 * @version    :
 */
public class PlaintextCredentialsMatcher extends HashedCredentialsMatcher
{
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
	{
		try
		{
			MyUsernamePasswordToken userNamePasswordToken = (MyUsernamePasswordToken) token;
			String loginPassword = String.valueOf(userNamePasswordToken.getPassword());	// 登录时输入的密码
			String userPassword = info.getCredentials().toString();						// 数据库存储的密码
			
			Integer loginUserType = userNamePasswordToken.getUserType();				// 登录时输入的用户类型
			Integer userType = ((Principal)info.getPrincipals().getPrimaryPrincipal()).getType();	// 数据库存储的用户类型
			
			//内容一致就返回true,不一致就返回false
			if((loginPassword.equals(userPassword)) && (loginUserType.equals(userType)))
			{
				return true;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}

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

import cn.nesc.general.authcenter.config.shiro.oauth2.OAuth2Token;
import cn.nesc.general.authcenter.model.TmUserPO;
import cn.nesc.general.authcenter.service.UserManagerService;
import cn.nesc.general.core.shiro.Principal;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;


/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2016年9月21日
 * @version    :
 */
public class IDAASCredentialsMatcher extends HashedCredentialsMatcher
{

	@Resource
	@Lazy
	UserManagerService userManagerService;


	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
	{
		try
		{
			if (token instanceof OAuth2Token)
			{
				// 从IDAAS登录，查询数据库是否存在该用户进行验证
				String userName = ((Principal) info.getPrincipals().getPrimaryPrincipal()).getName();
				TmUserPO user = userManagerService.getUserByCode(userName);
				return null == user ? false : true;
			}
			else
			{
				// 从页面登录，使用shiro的HashedCredentialsMatcher进行验证
				return super.doCredentialsMatch(token, info);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}
}

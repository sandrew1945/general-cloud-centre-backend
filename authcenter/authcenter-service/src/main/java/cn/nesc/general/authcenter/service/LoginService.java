package cn.nesc.general.authcenter.service;


import cn.nesc.general.authcenter.bean.login.LoginBO;
import cn.nesc.general.authcenter.model.TmUserPO;
import cn.nesc.general.authcenter.service.util.MenuNode;
import cn.nesc.general.core.bean.AclUserBean;
import cn.nesc.general.core.exception.ServiceException;

import java.util.List;


/**
 * Function    : 
 * @author     : zhao.feng
 * CreateDate  : 2010-11-5
 * @version    :
 */
public interface LoginService
{

	/**
	 *  登录
	 * @param user
	 * @return
	 * @throws ServiceException
	 */
	LoginBO login(TmUserPO user) throws ServiceException;

	/**
	 *  获取用户信息
	 * @return
	 * @throws ServiceException
	 */
	AclUserBean userInfo(AclUserBean loginUser) throws ServiceException;

	/**
	 * @Author summer
	 * @Description	根据sessionId获取session
	 * @Date 14:35 2022/2/14
	 * @Param [sessionId]
	 * @return boolean
	 **/
	boolean validateSession(String sessionId) throws ServiceException;

	/**
	 *
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	List<MenuNode> getMenuByRole(Integer roleId) throws ServiceException;

	/**
	 *  登出
	 * @throws ServiceException
	 */
	void logout() throws ServiceException;
	
}

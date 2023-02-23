package cn.nesc.general.authcenter.service;


import cn.nesc.general.authcenter.bean.RoleTreeNode;
import cn.nesc.general.authcenter.model.TmUserPO;
import cn.nesc.general.authcenter.service.util.MenuNode;
import cn.nesc.general.core.result.LoginResult;
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
	AclUserBean login(TmUserPO user) throws ServiceException;

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
	 *
	 * Function    : 处理用户的角色及Session信息
	 * LastUpdate  : 2017年5月24日
	 * @param userCode
	 * @return
	 * @throws ServiceException
	 */
	LoginResult postRoleHandler(String userCode) throws ServiceException;

	/**
	 *
	 * Function    : 根据用户ID获取角色选择树
	 * LastUpdate  : 2016年4月16日
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	List<RoleTreeNode> choiceRoleTree(Integer userId) throws ServiceException;

	/**
	 *
	 * Function    : 用户选择完角色进入主界面
	 * LastUpdate  : 2016年4月16日
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	String showIndex(Integer roleId) throws ServiceException;

	/**
	 *  登出
	 * @throws ServiceException
	 */
	void logout() throws ServiceException;
	
}

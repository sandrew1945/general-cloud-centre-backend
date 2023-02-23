package cn.nesc.general.authcenter.service;


import cn.nesc.general.authcenter.service.util.TreeNode;
import cn.nesc.general.core.bean.AclUserBean;
import cn.nesc.general.core.exception.ServiceException;

import java.util.List;


/**
 * Function    : 角色管理处理service
 * @author     : liutt
 * CreateDate  : 2016-5-30
 * @version    :
 */
public interface MenuManagerService
{
	/**
	 * 
	 * Function    : 获取全部角色信息
	 * LastUpdate  : 2016年5月27日
	 * @return
	 */
	List<TreeNode> getMenuTree() throws ServiceException;

	/**
	 *  更新菜单
	 * @param treeNode
	 * @return
	 * @throws ServiceException
	 */
	int updateMenu(TreeNode treeNode, AclUserBean loginUser) throws ServiceException;

	/**
	 *  删除菜单
	 * @param functionId
	 * @param loginUser
	 * @return
	 * @throws ServiceException
	 */
	int deleteMenuById(Integer functionId, AclUserBean loginUser) throws ServiceException;

	/**
	 *  创建菜单
	 * @param treeNode
	 * @param fatherId
	 * @param loginUser
	 * @return
	 * @throws ServiceException
	 */
	int createMenu(TreeNode treeNode, Integer fatherId, AclUserBean loginUser) throws ServiceException;
}

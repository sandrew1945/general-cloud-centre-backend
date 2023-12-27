package cn.nesc.general.authcenter.service;


import cn.nesc.general.authcenter.bean.RoleBean;
import cn.nesc.general.authcenter.model.TmRolePO;
import cn.nesc.general.core.bean.AclUserBean;
import cn.nesc.general.core.bean.PageResult;
import cn.nesc.general.core.exception.ServiceException;

import java.util.List;


/**
 * Function    : 角色管理处理service
 * @author     : liutt
 * CreateDate  : 2016-5-30
 * @version    :
 */
public interface RoleManagerService
{
	/**
	 * 
	 * Function    : 获取全部角色信息
	 * LastUpdate  : 2016年5月27日
	 * @return
	 */
	List<TmRolePO> getRoleList() throws ServiceException;
	
	/**
	 * Function    : 分页查询角色列表
	 * LastUpdate  : 2016年5月26日
	 * @author     ：liutt
	 * @param condition 查询条件
	 * @param curPage 分页信息
	 */
	PageResult<RoleBean> roleManagerPageQuery(TmRolePO condition, int limit, int curPage) throws ServiceException;
	

	/**
	 * Function    : 创建角色
	 * LastUpdate  : 2016年5月30日
	 * @author     ：liutt
	 * @param role 角色
	 * @param aclUser 登录用户
	 */
	boolean createRole(TmRolePO role, AclUserBean aclUser) throws ServiceException;
	
	/**
	 * Function    :根据角色id查询角色信息
	 * LastUpdate  : 2016年5月31日
	 * @author     ：liutt
	 * @param roleId 角色id
	 * @return
	 * @throws ServiceException
	 */
	TmRolePO findByroleId(Integer roleId) throws ServiceException;
	
	/**
	 * Function    :修改编辑角色信息
	 * LastUpdate  : 2016年5月31日
	 * @author     ：liutt
	 * @param role
	 * @param aclUser
	 * @return
	 * @throws ServiceException
	 */
	boolean updateRole(TmRolePO role, AclUserBean aclUser) throws ServiceException;
	

	/**
	 * Function    : 删除角色
	 * LastUpdate  : 2016年5月31日
	 * @author     ：liutt
	 * @param roleId 角色id
	 * @return
	 * @throws ServiceException
	 */
	boolean deleteRole(Integer roleId, AclUserBean aclUser) throws ServiceException;

	/**
	 *  保存角色下的菜单(quasar)
	 * @param roleId
	 * @param functionIds
	 * @param loginUser
	 * @return
	 * @throws ServiceException
	 */
	boolean saveSelectedFunc(Integer roleId, List<Integer> functionIds, AclUserBean loginUser) throws ServiceException;

	/**
	 * 	获取已选择菜单
	 * @param roleId
	 * @return
	 * @throws ServiceException
     */
	List<Integer> getCheckPermission(Integer roleId) throws ServiceException;

	boolean roleValidate(String roleCode) throws ServiceException;
}

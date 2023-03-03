package cn.nesc.general.authcenter.service;

import cn.nesc.general.authcenter.bean.usermanager.UserManagerDTO;
import cn.nesc.general.authcenter.bean.usermanager.UserManagerBO;
import cn.nesc.general.authcenter.model.TmRolePO;
import cn.nesc.general.authcenter.model.TmUserPO;
import cn.nesc.general.common.bean.AclUserBean;
import cn.nesc.general.common.bean.PageResult;
import cn.nesc.general.core.exception.ServiceException;
import cn.nesc.general.core.result.JsonResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Function    : 
 * @author     : zhao.feng
 * CreateDate  : 2010-11-5
 * @version    :
 */
public interface UserManagerService
{
	/**
	 * @Author summer
	 * @Description
	 * @Date 16:11 2023/2/24
	 * @Param [condition, limit, curPage]
	 * @return cn.nesc.general.core.bean.PageResult<cn.nesc.general.core.bean.AclUserBean>
	 **/
	PageResult<UserManagerBO> userManagerPageQuery(UserManagerDTO condition, int limit, int curPage) throws ServiceException;

	/**
	 * 
	 * Function    : 创建用户
	 * LastUpdate  : 2016年9月22日
	 * @param user
	 * @param avatar
	 * @param aclUser
	 * @return
	 * @throws ServiceException
	 */
	Boolean createUserInfo(UserManagerDTO user, MultipartFile avatar, AclUserBean aclUser) throws ServiceException;
	
	/**
	 * 
	 * Function    : 删除用户信息
	 * LastUpdate  : 2016年9月22日
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	JsonResult deleteUserInfo(Integer userId, AclUserBean aclUser) throws ServiceException;
	
	/**
	 * 根据用户id查询用户信息
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	UserManagerBO findByUserId(Integer userId) throws ServiceException;
	
	/**
	 * 
	 * Function    : 根据用户名获取用户信息
	 * LastUpdate  : 2016年10月25日
	 * @param userCode
	 * @return
	 * @throws ServiceException
	 */
	TmUserPO getUserByCode(String userCode) throws ServiceException;
	
	/**
	 * 修改编辑用户信息
	 * @param user
	 * @param avatar
	 * @param aclUser
	 * @return
	 * @throws ServiceException
	 */
	JsonResult updateUserInfo(TmUserPO user, MultipartFile avatar, AclUserBean aclUser) throws ServiceException;
	
	
	/**
	 * 
	 * Function    : 获取当前用户已经关联的角色
	 * LastUpdate  : 2016年9月22日
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	List<TmRolePO> getRelationRolesByUserId(Integer userId) throws ServiceException;
	
	/**
	 * 
	 * Function    : 删除用户与角色的关联
	 * LastUpdate  : 2016年9月22日
	 * @param userId
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	JsonResult deleteRoleRelation(Integer userId, Integer roleId) throws ServiceException;
	
	/**
	 * 
	 * Function    : 获取该用户下全部未关联角色
	 * LastUpdate  : 2016年9月22日
	 * @param aclUser
	 * @return
	 * @throws ServiceException
	 */
	List<TmRolePO> getUnRelationRoles(AclUserBean aclUser) throws ServiceException;
	
	/**
	 * 
	 * Function    : 批量添加用户与角色关系
	 * LastUpdate  : 2016年9月22日
	 * @param userId
	 * @param rolesStr
	 * @return
	 * @throws ServiceException
	 */
	JsonResult createRelation(Integer userId, String rolesStr, AclUserBean aclUser) throws ServiceException;

	/**
	 *  获取全部有效用户
	 * @return
	 * @throws ServiceException
     */
	JsonResult getAvailableUserList() throws ServiceException;

	/**
	 *  更新密码
	 * @param userId
	 * @param originPwd
	 * @param newPwd
	 * @return
	 * @throws ServiceException
     */
	JsonResult updatePassword(Integer userId, String originPwd, String newPwd, AclUserBean loginUser) throws ServiceException;

	/**
	 *  用户代码验证
	 * @param userCode
	 * @return
	 * @throws ServiceException
	 */
	boolean userValidate(String userCode) throws ServiceException;
}

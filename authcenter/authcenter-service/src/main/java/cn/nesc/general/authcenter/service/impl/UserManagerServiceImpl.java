/**
 * <pre>
 * FILE : LoginService.java
 * CLASS : LoginService
 *
 * AUTHOR : SuMMeR
 *
 * FUNCTION : TODO
 *
 *
 * ======================================================================
 * CHANGE HISTORY LOG
 * ----------------------------------------------------------------------
 * MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
 * ----------------------------------------------------------------------
 * 		  |2014年5月3日| SuMMeR| Created |
 * DESCRIPTION:
 * </pre>
 * <p>
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 */
/**
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 */

package cn.nesc.general.authcenter.service.impl;


import cn.nesc.general.authcenter.bean.RoleBean;
import cn.nesc.general.authcenter.bean.rolemanager.RoleManagerConvertor;
import cn.nesc.general.authcenter.bean.usermanager.UserManagerBO;
import cn.nesc.general.authcenter.bean.usermanager.UserManagerConvertor;
import cn.nesc.general.authcenter.bean.usermanager.UserManagerDTO;
import cn.nesc.general.authcenter.mapper.TmRolePOMapper;
import cn.nesc.general.authcenter.mapper.TmUserPOMapper;
import cn.nesc.general.authcenter.mapper.TrUserRolePOMapper;
import cn.nesc.general.authcenter.mapper.custom.UserManagerMapper;
import cn.nesc.general.authcenter.model.*;
import cn.nesc.general.authcenter.service.UserManagerService;
import cn.nesc.general.common.bean.AclUserBean;
import cn.nesc.general.common.bean.PageResult;
import cn.nesc.general.common.dictionary.Fixcode;
import cn.nesc.general.common.encrypt.MD5Encrypt;
import cn.nesc.general.common.mybatis.PageQueryBuilder;
import cn.nesc.general.common.utils.MagicOOO;
import cn.nesc.general.core.exception.ServiceException;
import cn.nesc.general.core.exception.TooManyResultsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2014年5月3日
 * @version    :
 */
@Service
@CacheConfig(cacheNames = "user")
@Slf4j
public class UserManagerServiceImpl implements UserManagerService
{

    @Resource
    private UserManagerMapper userManagerMapper;

    @Resource
    private TmUserPOMapper tmUserPOMapper;

    @Resource
    private TrUserRolePOMapper trUserRolePOMapper;

    @Resource
    private UserManagerConvertor userManagerConvertor;

    @Resource
    private RoleManagerConvertor roleManagerConvertor;

    @Resource
    private TmRolePOMapper tmRolePOMapper;

    @Override
    public PageResult<UserManagerBO> userManagerPageQuery(UserManagerDTO condition, int limit, int curPage) throws ServiceException
    {
        try
        {
            return PageQueryBuilder.pageQuery(userManagerMapper, "userManagerPageQuery", condition, curPage, limit);
        }
        catch (Exception e)
        {
            throw new ServiceException("用户列表查询失败", e);
        }
    }

    @Override
    public Boolean createUserInfo(UserManagerDTO user, MultipartFile avatar, AclUserBean aclUser) throws ServiceException
    {
        Boolean result = false;
        try
        {
            boolean isExits = false;
            TmUserPOExample example = new TmUserPOExample();
            TmUserPOExample.Criteria criteria = example.createCriteria();
            criteria.andUserCodeEqualTo(user.getUserCode());
            criteria.andIsDeleteEqualTo(Fixcode.IF_TYPE_NO.getCode());
            List<TmUserPO> list = tmUserPOMapper.selectByExample(example);

            isExits = (null != list && list.size() > 0) ? true : false;
            if (!isExits)
            {
                TmUserPO insertUser = new TmUserPO();
                MagicOOO.copyProperties(insertUser, user);
                insertUser.setIsDelete(Fixcode.IF_TYPE_NO.getCode());
                insertUser.setCreateBy(aclUser.getUserCode());
                insertUser.setCreateDate(new Date());
                insertUser.setPassword(MD5Encrypt.MD5Encode(user.getPassword()));
                if (tmUserPOMapper.insertSelective(insertUser) > 0)
                {
                    result = true;
                }
            }
            return result;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("创建用户失败", e);
        }
    }

    @Override
    public Boolean deleteUserInfo(Integer userId, AclUserBean aclUser) throws ServiceException
    {
        try
        {
            TmUserPO updateUser = new TmUserPO();
            updateUser.setUserId(userId);
            updateUser.setIsDelete(Fixcode.IF_TYPE_YES.getCode());
            updateUser.setUpdateDate(new Date());
            updateUser.setUpdateBy(aclUser.getUserCode());
            int count = tmUserPOMapper.updateByPrimaryKeySelective(updateUser);
            if (count > 0)
            {
                return true;
            }
            else
            {
                throw new ServiceException("要删除的用户不存在");
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }


    @Override
    public UserManagerBO findByUserId(Integer userId) throws ServiceException
    {

        try
        {
            log.debug("缓存中不存在用户信息,读取数据库...");
            TmUserPO tmUserPO = tmUserPOMapper.selectByPrimaryKey(userId);
            return userManagerConvertor.toUserManagerBO(tmUserPO);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("查询失败", e);
        }
    }

    @Override
    public TmUserPO getUserByCode(String userCode) throws ServiceException
    {
        try
        {
            if (null == userCode || "".equals(userCode))
            {
                return null;
            }
            TmUserPOExample example = new TmUserPOExample();
            TmUserPOExample.Criteria criteria = example.createCriteria();
            criteria.andUserCodeEqualTo(userCode);
            criteria.andIsDeleteEqualTo(Fixcode.IF_TYPE_NO.getCode());
            List<Integer> userStatus = new ArrayList<>();
            userStatus.add(Fixcode.STAFF_STATUS_JOB.getCode());
            userStatus.add(Fixcode.STAFF_STATUS_RECUPERATE.getCode());
            criteria.andUserStatusIn(userStatus);
            List<TmUserPO> users = tmUserPOMapper.selectByExample(example);

            if (null != users && users.size() == 1)
            {
                return users.get(0);
            }
            else
            {
                throw new TooManyResultsException("userCode为:" + userCode + "的用户重复");
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * @liutt
     * 修改编辑用户信息
     * @param user
     * @param avatar
     * @param aclUser
     * @return
     * @throws ServiceException
     */
    @Override
    public int updateUserInfo(UserManagerDTO user, MultipartFile avatar, AclUserBean aclUser) throws ServiceException
    {
        try
        {

            TmUserPO updateUser = userManagerConvertor.toUserPO(user);
            updateUser.setUpdateBy(aclUser.getUserCode());
            updateUser.setUpdateDate(new Date());

            //如果用户没有填写密码选项，则密码不改变
            if (user.getPassword().length() > 1)
            {
                updateUser.setPassword(MD5Encrypt.MD5Encode(user.getPassword()));
            }
            else
            {
                updateUser.setPassword(null);
            }
            return tmUserPOMapper.updateByPrimaryKeySelective(updateUser);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("编辑用户失败", e);
        }
    }

    @Override
    public List<RoleBean> getRelationRolesByUserId(Integer userId) throws ServiceException
    {
        try
        {
            List<TmRolePO> rolePOS = userManagerMapper.queryRelationRole(userId);
            return roleManagerConvertor.toRoleBOList(rolePOS);
        }
        catch (Exception e)
        {
            throw new ServiceException("获取已有角色失败", e);
        }

    }

    @Override
    public boolean deleteRoleRelation(Integer userId, Integer roleId) throws ServiceException
    {
        try
        {
            TrUserRolePOExample example = new TrUserRolePOExample();
            TrUserRolePOExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(userId);
            criteria.andRoleIdEqualTo(roleId);
            int count = trUserRolePOMapper.deleteByExample(example);
            if (count > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("删除用户角色失败", e);
        }
    }

    @Override
    public List<RoleBean> getUnRelationRoles(AclUserBean aclUser) throws ServiceException
    {
        try
        {

            List<RoleBean> roles = roleManagerConvertor.toRoleBOList(userManagerMapper.getRoleExistOwn(aclUser));
            return roles;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取未关联角色失败", e);
        }

    }

    @Override
    public void createRelation(Integer userId, String rolesStr, AclUserBean aclUser) throws ServiceException
    {
        try
        {
            // 验证用户ID有效
            if (null == tmUserPOMapper.selectByPrimaryKey(userId))
            {
                throw new ServiceException("用户不存在, userId: " + userId);
            }
            String[] roles = rolesStr.split(",");
            for (String roleId : roles)
            {
                // 验证角色
                if (null == tmRolePOMapper.selectByPrimaryKey(new Integer(roleId)))
                {
                    throw new ServiceException("角色不存在, roleId: " + roleId);
                }
                TrUserRolePO userRole = new TrUserRolePO();
                userRole.setUserId(userId);
                userRole.setRoleId(new Integer(roleId));
                userRole.setCreateBy(aclUser.getUserCode());
                userRole.setCreateDate(new Date());
                trUserRolePOMapper.insertSelective(userRole);
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }

    }

    @Override
    public List<TmUserPO> getAvailableUserList() throws ServiceException
    {
        try
        {
            TmUserPOExample example = new TmUserPOExample();
            TmUserPOExample.Criteria criteria = example.createCriteria();
            criteria.andIsDeleteEqualTo(Fixcode.IF_TYPE_NO.getCode());
            criteria.andUserStatusNotEqualTo(Fixcode.STAFF_STATUS_DIMISSION.getCode());
            List<TmUserPO> list = tmUserPOMapper.selectByExample(example);

            if (null != list && list.size() > 0)
            {
                return list;
            }
            else
            {
                throw new ServiceException("有效用户不存在");
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("查询用户失败", e);
        }
    }

    @Override
    public boolean updatePassword(Integer userId, String originPwd, String newPwd, AclUserBean loginUser) throws ServiceException
    {
        try
        {
            // 验证原始密码是否正确
            TmUserPO userPO = tmUserPOMapper.selectByPrimaryKey(userId);
            if (null != userPO && !MD5Encrypt.MD5Encode(originPwd).equals(userPO.getPassword()))
            {
                throw new ServiceException("输入的密码不正确");
            }
            else
            {
                // 更新密码
                TmUserPO updateUser = new TmUserPO();
                updateUser.setUserId(userId);
                updateUser.setPassword(MD5Encrypt.MD5Encode(newPwd));
                updateUser.setUpdateBy(loginUser.getUserCode());
                updateUser.setUpdateDate(new Date());
                int count = tmUserPOMapper.updateByPrimaryKeySelective(updateUser);
                if (count > 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("修改密码失败", e);
        }
    }

    @Override
    public boolean userValidate(String userCode) throws ServiceException
    {
        try
        {
            TmUserPOExample example = new TmUserPOExample();
            TmUserPOExample.Criteria criteria = example.createCriteria();
            criteria.andUserCodeEqualTo(userCode);
            criteria.andIsDeleteEqualTo(Fixcode.IF_TYPE_NO.getCode());
            criteria.andUserStatusNotEqualTo(Fixcode.STAFF_STATUS_DIMISSION.getCode());
            List<TmUserPO> userList = tmUserPOMapper.selectByExample(example);
            return userList.size() > 0 ? false : true;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("用户代码验证失败", e);
        }
    }
}

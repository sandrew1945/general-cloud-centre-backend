/**
 * <pre>
 * FILE : RoleManagerServiceImpl.java
 * CLASS : RoleManagerServiceImpl
 *
 * AUTHOR : Liutt
 *
 * FUNCTION : TODO
 *
 *
 * ======================================================================
 * CHANGE HISTORY LOG
 * ----------------------------------------------------------------------
 * MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
 * ----------------------------------------------------------------------
 * 		  |2016年5月30日| Liutt| Created |
 * DESCRIPTION:
 * </pre>
 * <p>
 * $Id: RoleManagerServiceImpl.java,v 0.1 2016年5月30日 上午10:58:34 liutt Exp $
 * <p>
 * $Id: RoleManagerServiceImpl.java,v 0.1 2016年5月30日 上午10:58:34 liutt Exp $
 * <p>
 * $Id: RoleManagerServiceImpl.java,v 0.1 2016年5月30日 上午10:58:34 liutt Exp $
 */
/**
 * $Id: RoleManagerServiceImpl.java,v 0.1 2016年5月30日 上午10:58:34 liutt Exp $
 */

package cn.nesc.general.authcenter.service.impl;


import cn.nesc.general.authcenter.bean.RoleBean;
import cn.nesc.general.authcenter.mapper.TmMenuPOMapper;
import cn.nesc.general.authcenter.mapper.TmRolePOMapper;
import cn.nesc.general.authcenter.mapper.TrRoleFuncPOMapper;
import cn.nesc.general.authcenter.mapper.custom.RoleManagerMapper;
import cn.nesc.general.authcenter.model.*;
import cn.nesc.general.authcenter.service.RoleManagerService;
import cn.nesc.general.common.bean.AclUserBean;
import cn.nesc.general.common.bean.PageResult;
import cn.nesc.general.common.dictionary.Constants;
import cn.nesc.general.common.dictionary.Fixcode;
import cn.nesc.general.common.mybatis.PageQueryBuilder;
import cn.nesc.general.core.exception.DAOException;
import cn.nesc.general.core.exception.ServiceException;
import cn.nesc.general.core.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Function    : 
 * @author     : liutt
 * CreateDate  : 2016年5月30日
 * @version    :
 */
@Service
@Slf4j
public class RoleManagerServiceImpl implements RoleManagerService
{
    @Resource
    private TmRolePOMapper tmRolePOMapper;

    @Resource
    private RoleManagerMapper roleManagerMapper;

    @Resource
    private TmMenuPOMapper tmMenuPOMapper;

    @Resource
    private TrRoleFuncPOMapper trRoleFuncPOMapper;


    @Override
    public List<TmRolePO> getRoleList() throws ServiceException
    {
        try
        {
            TmRolePOExample example = new TmRolePOExample();
            TmRolePOExample.Criteria criteria = example.createCriteria();
            criteria.andIsDeleteEqualTo(Fixcode.IF_TYPE_NO.getCode());

            List<TmRolePO> roleList = tmRolePOMapper.selectByExample(example);
            log.debug("roleList : " + roleList);
            return roleList;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取角色列表失败", e);
        }

    }

    /**
     * Function    : 分页查询角色列表
     * LastUpdate  : 2016年5月26日
     * @author     ：liutt
     * @param condition 查询条件
     * @param curPage 分页信息
     */
    @Override
    public PageResult<RoleBean> roleManagerPageQuery(TmRolePO condition, int limit, int curPage) throws ServiceException
    {
        try
        {
            return PageQueryBuilder.pageQuery(roleManagerMapper, "roleManagerPageQuery", condition, curPage, limit);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("角色列表查询失败", e);
        }
    }

    /**
     * Function    : 创建角色
     * LastUpdate  : 2016年5月30日
     * @author     ：liutt
     * @param role 角色
     * @param aclUser 登录用户
     */
    @Override
    public boolean createRole(TmRolePO role, AclUserBean aclUser) throws ServiceException
    {
        try
        {
            boolean isExits = false;
            // 验证用户代码是否存在
            TmRolePOExample example = new TmRolePOExample();
            TmRolePOExample.Criteria criteria = example.createCriteria();
            criteria.andRoleCodeEqualTo(role.getRoleCode());
            criteria.andIsDeleteEqualTo(Fixcode.IF_TYPE_NO.getCode());
            List<TmRolePO> list = tmRolePOMapper.selectByExample(example);

            isExits = (null != list && list.size() > 0) ? true : false;
            if (!isExits)
            {
                role.setIsDelete(Constants.IF_TYPE_NO);
                role.setCreateBy(aclUser.getUserCode());
                role.setCreateDate(new Date());
                int count = tmRolePOMapper.insertSelective(role);
                if (count > 0)
                {
                    return true;
                }
                return false;
            }
            else
            {
                throw new ServiceException("角色已经存在");
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("创建角色失败", e);
        }
    }

    /**
     * Function    :根据角色id查询角色信息
     * LastUpdate  : 2016年5月31日
     * @author     ：liutt
     * @param roleId 角色id
     * @return
     * @throws ServiceException
     */
    @Override
    public TmRolePO findByroleId(Integer roleId) throws ServiceException
    {

        try
        {
            TmRolePO tmRolePO = tmRolePOMapper.selectByPrimaryKey(roleId);
            return tmRolePO;
        }
        catch (DAOException e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("查询角色失败", e);
        }
    }

    /**
     * Function    :修改编辑角色信息
     * LastUpdate  : 2016年5月31日
     * @author     ：liutt
     * @param role
     * @param aclUser
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean updateRole(TmRolePO role, AclUserBean aclUser) throws ServiceException
    {
        JsonResult result = new JsonResult();
        try
        {
            role.setUpdateDate(new Date());
            role.setUpdateBy(aclUser.getUserCode());
            int count = tmRolePOMapper.updateByPrimaryKeySelective(role);
            if (count > 0)
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("编辑角色失败", e);
        }

    }

    /**
     * Function    : 删除角色
     * LastUpdate  : 2016年5月31日
     * @author     ：
     * @param roleId 角色id
     * @return
     * @throws ServiceException
     */
    @Override
    public boolean deleteRole(Integer roleId, AclUserBean aclUser) throws ServiceException
    {
        try
        {
            List<TrUserRolePO> userRoles = roleManagerMapper.userRoleByRoleId(roleId);
            if (userRoles.size() > 0)
            {
                throw new ServiceException("该角色已关联用户，无法删除");
            }
            TmRolePO deleteRole = new TmRolePO();
            deleteRole.setRoleId(roleId);
            deleteRole.setIsDelete(Fixcode.IF_TYPE_YES.getCode());
            deleteRole.setUpdateBy(aclUser.getUserCode());
            deleteRole.setUpdateDate(new Date());
            int count = tmRolePOMapper.updateByPrimaryKeySelective(deleteRole);
            if (count > 0)
            {
                return true;
            }
            return false;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("删除角色失败", e);
        }
    }

    /**
     *  保存权限
     * @param roleId
     * @param functionIds
     * @param loginUser
     * @return
     * @throws ServiceException
     */

    @Override
    public boolean saveSelectedFunc(Integer roleId, List<Integer> functionIds, AclUserBean loginUser) throws ServiceException
    {
        try
        {
            List<Integer> insertedIds = new ArrayList<>();
            // 先清空该角色下全部功能
//            TrRoleFun example = new TrRoleFuncFrontPOExample();
//            TrRoleFuncFrontPOExample.Criteria criteria = example.createCriteria();
//            criteria.andRoleIdEqualTo(roleId);
//            trRoleFuncFrontPOMapper.deleteByExample(example);

            // 重新生成角色功能关系
            functionIds.stream().forEach(functionId -> {
                createRoleFunction(roleId, functionId, insertedIds, loginUser);
            });
            return true;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("添加功能失败", e);
        }
    }

    private int createRoleFunction(Integer roleId, Integer functionId, List<Integer> createdFunction, AclUserBean loginUser)
    {
        try
        {
            // 插入角色下function id, 如果该function有父function,那么先插入父function，最后记录插入过的functionId
            if (createdFunction.contains(functionId))
            {
                // 已经存在，不需要保存
                return 0;
            }
            // 判断是否有父节点
            TmMenuPO menu = tmMenuPOMapper.selectByPrimaryKey(functionId);
            if (null != menu.getFatherId())
            {
                createRoleFunction(roleId, menu.getFatherId(), createdFunction, loginUser);
            }
            // 将该节点关联到该角色下
            TrRoleFuncPO insertRoleFunc = new TrRoleFuncPO();
            insertRoleFunc.setRoleId(roleId);
            insertRoleFunc.setMenuId(functionId);
            insertRoleFunc.setCreateBy(loginUser.getUserCode());
            insertRoleFunc.setCreateDate(new Date());
            int count = trRoleFuncPOMapper.insertSelective(insertRoleFunc);
            createdFunction.add(functionId);
            return count;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new RuntimeException("添加功能失败", e);
        }
    }

    @Override
    public List<Integer> getCheckPermission(Integer roleId) throws ServiceException
    {
        try
        {
            TrRoleFuncPOExample example = new TrRoleFuncPOExample();
            TrRoleFuncPOExample.Criteria criteria = example.createCriteria();
            criteria.andRoleIdEqualTo(roleId);
            List<TrRoleFuncPO> roleFuncFrontList = trRoleFuncPOMapper.selectByExample(example);
            List<Integer> checkedPermission = new ArrayList<>();
            roleFuncFrontList.stream().forEach(el -> {
                checkedPermission.add(el.getMenuId());
            });
            return checkedPermission;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取已选菜单失败", e);
        }
    }

    @Override
    public boolean roleValidate(String roleCode) throws ServiceException
    {
        try
        {
            TmRolePOExample example = new TmRolePOExample();
            TmRolePOExample.Criteria criteria = example.createCriteria();
            criteria.andRoleCodeEqualTo(roleCode);
            criteria.andIsDeleteEqualTo(Constants.IF_TYPE_NO);
            List<TmRolePO> roleList = tmRolePOMapper.selectByExample(example);
            return roleList.size() > 0 ? false : true;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("验证角色是否存在失败", e);
        }
    }
}

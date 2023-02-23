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
 * <p>
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 * <p>
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 * <p>
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 * <p>
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 */
/**
 * $Id: LoginService.java,v 0.1 2014年5月3日 上午10:58:34 SuMMeR Exp $
 */

package cn.nesc.general.authcenter.service.impl;


import cn.nesc.general.authcenter.bean.RoleTreeNode;
import cn.nesc.general.authcenter.bean.UserInfo;
import cn.nesc.general.authcenter.dao.CommonDAO;
import cn.nesc.general.authcenter.dao.LoginDAO;
import cn.nesc.general.authcenter.model.TmFunctionPO;
import cn.nesc.general.authcenter.model.TmRolePO;
import cn.nesc.general.authcenter.model.TmUserPO;
import cn.nesc.general.authcenter.service.LoginService;
import cn.nesc.general.authcenter.service.UserManagerService;
import cn.nesc.general.authcenter.service.util.MenuNode;
import cn.nesc.general.authcenter.service.util.UserUtil;
import cn.nesc.general.core.result.LoginResult;
import cn.nesc.general.common.dictionary.Constants;
import cn.nesc.general.core.bean.AclUserBean;
import cn.nesc.general.core.exception.ServiceException;
import cn.nesc.general.core.shiro.MyUsernamePasswordToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Function    : 
 * @author     : SuMMeR
 * CreateDate  : 2014年5月3日
 * @version    :
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService
{
    @Resource
    private LoginDAO loginDAO;

    @Resource
    private UserManagerService userManagerService;

    @Resource
    private CommonDAO commonDAO;


    @Override
    public AclUserBean login(TmUserPO user) throws ServiceException
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            MyUsernamePasswordToken token = new MyUsernamePasswordToken(user.getUserCode(), user.getPassword());

            if (!subject.isAuthenticated())
            {
                subject.login(token);
                TmUserPO databaseUser = userManagerService.getUserByCode(user.getUserCode());
                AclUserBean loginUser = new AclUserBean();
                loginUser.setUserId(databaseUser.getUserId());
                loginUser.setToken(subject.getSession().getId().toString());
                subject.getSession().setAttribute(Constants.LOGIN_USER, loginUser);
                return loginUser;
            }
            else
            {
                AclUserBean loginUser = (AclUserBean) subject.getSession().getAttribute(Constants.LOGIN_USER);
                return loginUser;
            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("登录失败", e);
        }
    }

    @Override
    public AclUserBean userInfo(AclUserBean loginUser) throws ServiceException
    {
        try
        {
            TmUserPO user = userManagerService.findByUserId(loginUser.getUserId());
            loginUser.setUserCode(user.getUserCode());
            loginUser.setUserName(user.getUserName());
            loginUser.setSex(user.getSex());
            loginUser.setUserType(user.getUserType());
            loginUser.setPhone(user.getPhone());
            loginUser.setMobile(user.getMobile());
            loginUser.setEmail(user.getEmail());
            loginUser.setAvatarPath(user.getAvatar());
            List<TmRolePO> roleList = userManagerService.getRelationRolesByUserId(loginUser.getUserId());
            loginUser.setRoleList(roleList);
            return loginUser;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取用户信息失败", e);
        }
    }

    @Override
    public boolean validateSession(String sessionId) throws ServiceException
    {
        try
        {
            DefaultSessionKey sessionKey = new DefaultSessionKey(sessionId);
            Session session = SecurityUtils.getSecurityManager().getSession(sessionKey);
            if (null != session)
            {
                return true;
            }

        }
        catch (Exception e)
        {
            log.debug("There is no session with id [{}]", sessionId);
//            throw new ServiceException("获取session失败", e);
        }
        return false;
    }

    @Override
    public List<MenuNode> getMenuByRole(Integer roleId) throws ServiceException
    {
        try
        {
//            TmFuncFrontPO cond = new TmFuncFrontPO();
//            cond.setRoleId(roleId);
//            List<TmFuncFrontPO> functionList = commonDAO.select(cond);
            List<TmFunctionPO> functionList = loginDAO.getMenuByRole(roleId);

            List<MenuNode> menuList = new ArrayList<>();
            Map<Integer, MenuNode> cache = new HashMap<>();
            for (TmFunctionPO function : functionList)
            {
                MenuNode menu = new MenuNode();
                menu.setPath(function.getPath());
                menu.setName(function.getTitle());
                Map<String, String> meta = new HashMap<>();
                meta.put("title", function.getTitle());
                meta.put("icon", function.getIcon());
                menu.setMeta(meta);
                cache.put(function.getFunctionId(), menu);
                if (null != function.getFatherId())
                {
                    // 如果存在父节点，那么将子节点添加到父节点，并且不添加到menuList中
                    MenuNode father = cache.get(function.getFatherId());
                    father.addChildren(menu);
                    continue;
                }
                menuList.add(menu);
            }
            return menuList;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("获取系统菜单失败", e);
        }
    }

    /**
     * Function    : 用户角色选择处理器
     * LastUpdate  : 2014年6月4日
     * @param userCode
     * @return
     */
    public LoginResult postRoleHandler(String userCode) throws ServiceException
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            TmUserPO sessionUser = userManagerService.getUserByCode(userCode);
            AclUserBean aclUser = new AclUserBean();
            aclUser.setUserId(sessionUser.getUserId());
            aclUser.setUserCode(sessionUser.getUserCode());
            aclUser.setUserName(sessionUser.getUserName());
            aclUser.setUserType(sessionUser.getUserType());
            aclUser.setAvatarPath(sessionUser.getAvatar());
            session.setAttribute(Constants.LOGIN_USER, aclUser);

            // 如果已经选择完角色，那么直接进入系统首页
            if (UserUtil.hasRole(aclUser))
            {
                return new LoginResult("index", false);
            }
            // 判断用户可以选择的角色，如果只有一个，那么不进行选择，直接跳入系统首页
            List<UserInfo> list = loginDAO.selectRoleByUserCode(userCode);
            if (null != list && list.size() > 1)
            {
                // 多角色
                return new LoginResult("login/postRoleChoice", false);
            }
            if (null != list && list.size() < 1)
            {
                // 无角色
                return new LoginResult("redirect:error403", true);
            }

            // 角色信息放入SESSION中
            UserInfo tempAcl = list.get(0);
            aclUser.setRoleId(tempAcl.getRoleId());
            aclUser.setRoleName(tempAcl.getRoleName());
            aclUser.setRoleCode(tempAcl.getRoleCode());
            session.setAttribute(Constants.LOGIN_USER, aclUser);
            return new LoginResult("index", false);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("角色选择失败", e);
        }

    }

    /**
     * 	根据用户ID获取角色选择树
     */
    @Override
    public List<RoleTreeNode> choiceRoleTree(Integer userId) throws ServiceException
    {
        try
        {
            return loginDAO.selectRoleForChoice(userId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            throw new ServiceException("角色选择树获取失败", e);
        }
    }

    @Override
    public String showIndex(Integer roleId) throws ServiceException
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            AclUserBean aclUser = (AclUserBean) session.getAttribute(Constants.LOGIN_USER);
            // 通过roleId获取角色信息
            TmRolePO rolePO = commonDAO.selectById(new TmRolePO(roleId));
            aclUser.setRoleId(roleId);
            aclUser.setRoleCode(rolePO.getRoleCode());
            aclUser.setRoleName(rolePO.getRoleName());
            session.setAttribute(Constants.LOGIN_USER, aclUser);
            return "index";
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("选择岗位、角色失败", e);
        }
    }

    @Override
    public void logout() throws ServiceException
    {
        try
        {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new ServiceException("登出系统失败", e);
        }
    }
}

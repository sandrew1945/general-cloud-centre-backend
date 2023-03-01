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


import cn.nesc.general.authcenter.mapper.custom.LoginMapper;
import cn.nesc.general.authcenter.model.TmMenuPO;
import cn.nesc.general.authcenter.model.TmRolePO;
import cn.nesc.general.authcenter.model.TmUserPO;
import cn.nesc.general.authcenter.service.LoginService;
import cn.nesc.general.authcenter.service.UserManagerService;
import cn.nesc.general.authcenter.service.util.MenuNode;
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
    private LoginMapper loginMapper;
    @Resource
    private UserManagerService userManagerService;



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
            List<TmMenuPO> functionList = loginMapper.getMenuByRole(roleId);

            List<MenuNode> menuList = new ArrayList<>();
            Map<Integer, MenuNode> cache = new HashMap<>();
            for (TmMenuPO function : functionList)
            {
                MenuNode menu = new MenuNode();
                menu.setPath(function.getPath());
                menu.setName(function.getTitle());
                Map<String, String> meta = new HashMap<>();
                meta.put("title", function.getTitle());
                meta.put("icon", function.getIcon());
                menu.setMeta(meta);
                cache.put(function.getMenuId(), menu);
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

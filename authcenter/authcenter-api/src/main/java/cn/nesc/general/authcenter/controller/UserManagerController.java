package cn.nesc.general.authcenter.controller;


import cn.nesc.general.authcenter.bean.usermanager.UserManagerBO;
import cn.nesc.general.authcenter.bean.usermanager.UserManagerConvertor;
import cn.nesc.general.authcenter.bean.usermanager.UserManagerDTO;
import cn.nesc.general.authcenter.bean.usermanager.UserPageQueryVO;
import cn.nesc.general.authcenter.model.TmUserVO;
import cn.nesc.general.authcenter.service.UserManagerService;
import cn.nesc.general.common.bean.AclUserBean;
import cn.nesc.general.common.bean.PageResult;
import cn.nesc.general.common.controller.BaseController;
import cn.nesc.general.core.exception.JsonException;
import cn.nesc.general.core.exception.ServiceException;
import cn.nesc.general.core.result.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/usermanager")
public class UserManagerController extends BaseController
{
    @Resource
    private UserManagerService userManagerService;

    @Resource
    private UserManagerConvertor userManagerConvertor;

    @PostMapping(value = "/userManagerPageQuery")
    public
    @ResponseBody PageResult<UserPageQueryVO> userManagerPageQuery(@RequestParam(required = false) String userCode, @RequestParam(required = false) String userName, @RequestParam(required = false) Integer userStatus, int limit, int curPage) throws JsonException
    {
        try
        {
            UserManagerDTO condition = new UserManagerDTO();
            condition.setUserCode(userCode);
            condition.setUserName(userName);
            condition.setUserStatus(userStatus);
            // BO转VO
            PageResult<UserManagerBO> pageResult = userManagerService.userManagerPageQuery(condition, limit, curPage);

            PageResult<UserPageQueryVO> result = pageResult.convert(originPageResult -> {
                return userManagerConvertor.toUserPageQueryVO(originPageResult);
            });
            return result;
        }
        catch (Exception e)
        {
            throw new JsonException(e.getMessage(), e);
        }

    }

    @GetMapping("getUserInfoById")
    public @ResponseBody TmUserVO getUserInfoById(Integer userId) throws JsonException
    {
        try
        {
            UserManagerBO user = userManagerService.findByUserId(userId);
            return userManagerConvertor.toUserVO(user);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException("获取用户信息失败", e);
        }
    }

    @PostMapping(value = "/createUserInfo")
    public
    @ResponseBody
    Boolean createUserInfo(UserManagerDTO user) throws JsonException
    {
        try
        {
            return userManagerService.createUserInfo(user, null, getLoginUser());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }


    @PostMapping(value = "/updateUserInfo")
    public
    @ResponseBody
    Boolean updateUserInfo(UserManagerDTO user) throws JsonException
    {
        try
        {
            int count = userManagerService.updateUserInfo(user, null, getLoginUser());
            return count > 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }

    @PostMapping(value = "/deleteUserInfo")
    public
    @ResponseBody
    JsonResult deleteUserInfo(Integer userId) throws JsonException
    {
        try
        {
            return userManagerService.deleteUserInfo(userId, getLoginUser());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/queryRelationRoles")
    public
    @ResponseBody
    JsonResult queryRelationRoles(Integer userId) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            return result.requestSuccess(userManagerService.getRelationRolesByUserId(userId));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }


    @PostMapping(value = "/deleteRoleRelation")
    public
    @ResponseBody
    JsonResult deleteRoleRelation(Integer userId, Integer roleId) throws JsonException
    {
        try
        {
            return userManagerService.deleteRoleRelation(userId, roleId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }

    @PostMapping(value = "/queryUnRelationRoles")
    public
    @ResponseBody
    JsonResult queryUnRelationRoles(Integer userId, String roleName) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            AclUserBean aclUser = new AclUserBean();
            aclUser.setUserId(userId);
            aclUser.setRoleName(roleName);
            return result.requestSuccess(userManagerService.getUnRelationRoles(aclUser));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }


    @PostMapping(value = "/createRelation")
    public
    @ResponseBody
    JsonResult createRelation(Integer userId, String rolesStr) throws JsonException
    {
        try
        {
            return userManagerService.createRelation(userId, rolesStr, getLoginUser());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new JsonException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/getUserList")
    public @ResponseBody JsonResult getUserList() throws JsonException
    {
        try
        {
            return userManagerService.getAvailableUserList();
        }
        catch (ServiceException e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }

    @PostMapping(value = "/updatePassword")
    public @ResponseBody JsonResult updatePassword(Integer userId, String originPwd, String newPwd) throws JsonException
    {
        try
        {
            return userManagerService.updatePassword(userId, originPwd, newPwd, getLoginUser());
        }
        catch (ServiceException e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }

    @GetMapping(value = "/userValidate")
    public @ResponseBody JsonResult userValidate(String userCode) throws JsonException
    {
        JsonResult result = new JsonResult();
        try
        {
            return result.requestSuccess(userManagerService.userValidate(userCode));
        }
        catch (ServiceException e)
        {
            log.error(e.getMessage(), e);
            throw new JsonException(e.getMessage(), e);
        }
    }
}

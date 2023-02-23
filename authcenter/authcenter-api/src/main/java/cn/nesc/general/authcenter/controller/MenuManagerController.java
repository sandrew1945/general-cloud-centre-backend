/**********************************************************************
* <pre>
* FILE : RoleManagerController.java
* CLASS : RoleManagerController
*
* AUTHOR : Liutt
*
* FUNCTION : TODO
*
*
*======================================================================
* CHANGE HISTORY LOG
*----------------------------------------------------------------------
* MOD. NO.| DATE | NAME | REASON | CHANGE REQ.
*----------------------------------------------------------------------
* 		  |2016年5月30日| Liutt| Created |
* DESCRIPTION:
* </pre>
***********************************************************************/
/**
* $Id: RoleManagerController.java,v 0.1 2016年5月30日 上午10:58:34 liutt Exp $
*/
package cn.nesc.general.authcenter.controller;


import cn.nesc.general.authcenter.service.MenuManagerService;
import cn.nesc.general.authcenter.service.util.TreeNode;
import cn.nesc.general.core.result.JsonResult;
import cn.nesc.general.core.controller.BaseController;
import cn.nesc.general.core.exception.JsonException;
import cn.nesc.general.core.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


/**
 * Function    : 
 * @author     : liutt
 * CreateDate  : 2016年5月30日
 * @version    :
 */
@Slf4j
@Controller
@RequestMapping("/menumanager")
public class MenuManagerController extends BaseController
{
	@Resource
	private MenuManagerService menuManagerService;



	@GetMapping("getMenuTree")
	public @ResponseBody JsonResult getMenuTree() throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			List<TreeNode> menuNodeList = menuManagerService.getMenuTree();
			return result.requestSuccess(menuNodeList);
		}
		catch (ServiceException e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/createMenu")
	public @ResponseBody
	JsonResult createRole(TreeNode treeNode, Integer fatherId) throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			int count = menuManagerService.createMenu(treeNode, fatherId, getLoginUser());
			return result.requestSuccess(count);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new JsonException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/updateMenu")
	public @ResponseBody
	JsonResult updateMenu(TreeNode treeNode) throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			int count = menuManagerService.updateMenu(treeNode, getLoginUser());
			return result.requestSuccess(count);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new JsonException(e.getMessage(), e);
		}
	}

	@PostMapping(value = "/deleteMenu")
	public @ResponseBody
	JsonResult deleteMenu(Integer functionId) throws JsonException
	{
		JsonResult result = new JsonResult();
		try
		{
			int count = menuManagerService.deleteMenuById(functionId, getLoginUser());
			return result.requestSuccess(count);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
			throw new JsonException(e.getMessage(), e);
		}
	}
//
//	@PostMapping("savePermission")
//	public @ResponseBody JsonResult savePermission(@RequestBody PermissionParam parameter) throws JsonException
//	{
//		try
//		{
//			return roleManagerService.savePermission(parameter.getRoleId(), parameter.getNode(), getLoginUser());
//		}
//		catch (Exception e)
//		{
//			log.error(e.getMessage(), e);
//			throw new JsonException("保存权限失败", e);
//		}
//	}

}

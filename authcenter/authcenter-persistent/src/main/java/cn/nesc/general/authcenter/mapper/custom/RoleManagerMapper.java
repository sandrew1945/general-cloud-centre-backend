package cn.nesc.general.authcenter.mapper.custom;


import cn.nesc.general.authcenter.bean.RoleBean;
import cn.nesc.general.authcenter.model.TrUserRolePO;
import cn.nesc.general.common.mybatis.Pager;

import java.util.List;


/**
 * 角色管理mapper
 * @author liutingting
 *
 */
public interface RoleManagerMapper
{
	public List<RoleBean> roleManagerPageQuery(Pager pager);
	/**
	 * 
	 * Function    : 根据居然色id查询用户角色关系表
	 * LastUpdate  : 2016年5月31日
	 * @param roleId
	 * @return
	 */
	public List<TrUserRolePO> userRoleByRoleId(Integer roleId);

}
package cn.nesc.general.authcenter.mapper.custom;


import cn.nesc.general.authcenter.model.TmMenuPO;

import java.util.List;


public interface MenuManagerMapper
{
	// 查询系统菜单
	public List<TmMenuPO> getMenuList();

}
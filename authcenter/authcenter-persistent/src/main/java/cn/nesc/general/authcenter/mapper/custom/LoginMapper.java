package cn.nesc.general.authcenter.mapper.custom;


import cn.nesc.general.authcenter.model.TmMenuPO;
import cn.nesc.general.authcenter.model.TmUserPO;
import cn.nesc.general.common.bean.AclUserBean;
import cn.nesc.general.common.mybatis.Pager;

import java.util.List;

public interface LoginMapper
{
	List<TmUserPO> selectByUserCode(TmUserPO userPO);

	List<TmUserPO> pageQueryUser(Pager pager);

	List<AclUserBean> selectRoleByUserCode(String userCode);

	List<TmMenuPO> getMenuByRole(Integer roleId);
}
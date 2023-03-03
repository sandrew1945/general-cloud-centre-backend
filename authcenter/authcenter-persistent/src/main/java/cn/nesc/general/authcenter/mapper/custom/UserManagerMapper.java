package cn.nesc.general.authcenter.mapper.custom;


import cn.nesc.general.authcenter.model.TmRolePO;
import cn.nesc.general.authcenter.model.TmUserPO;
import cn.nesc.general.common.bean.AclUserBean;
import cn.nesc.general.common.mybatis.Pager;

import java.util.List;
import java.util.Map;


public interface UserManagerMapper
{
	public List<TmUserPO> userManagerPageQuery(Pager pager);
	
	public List<TmRolePO> queryRelationRole(Integer userId);
	
	public List<TmRolePO> getRoleExistOwn(AclUserBean userBean);

	public int updateClearAvatar(Map<String, Object> paramMap);
}
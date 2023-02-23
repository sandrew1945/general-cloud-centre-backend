package cn.nesc.general.authcenter.dao;


import cn.nesc.general.authcenter.bean.RoleBean;
import cn.nesc.general.common.dictionary.Constants;
import cn.nesc.general.core.exception.DAOException;
import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.bean.PageResult;
import com.sandrew.bury.callback.POCallBack;
import com.sandrew.bury.util.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by summer on 2019/7/26.
 */
@Slf4j
@Repository
public class RoleManagerDAO
{
    @Resource
    SqlSessionFactory sessionFactory;

    /**
     *  分页查询角色列表
     * @param roleCode
     * @param roleName
     * @param roleStatus
     * @param limit
     * @param curPage
     * @return
     * @throws DAOException
     */
    public PageResult<RoleBean> roleManagerPageQuery(String roleCode, String roleName, Integer roleStatus, int limit, int curPage) throws DAOException
    {
        Session session = sessionFactory.openSession();
        Parameters params = new Parameters();
        params.addParam(Constants.IF_TYPE_NO);
        StringBuilder sql = new StringBuilder();
        sql.append("select * from tm_role\n");
        sql.append("where 1 = 1\n");
        sql.append("and is_delete = ?\n");
        if (StringUtils.isNotEmpty(roleCode))
        {
            sql.append("and role_code like ?\n");
            params.addLikeParam(roleCode);
        }
        if (StringUtils.isNotEmpty(roleName))
        {
            sql.append("and role_name like ?\n");
            params.addLikeParam(roleName);
        }
        if (null != roleStatus)
        {
            sql.append("and role_status = ?\n");
            params.addParam(roleStatus);
        }
        return session.pageQuery(sql.toString(), params.getParams(), new POCallBack<>(RoleBean.class), limit, curPage);
    }
}

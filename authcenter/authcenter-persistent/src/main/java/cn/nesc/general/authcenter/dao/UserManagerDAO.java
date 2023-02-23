package cn.nesc.general.authcenter.dao;


import cn.nesc.general.authcenter.model.TmRolePO;
import cn.nesc.general.common.dictionary.Constants;
import cn.nesc.general.core.bean.AclUserBean;
import cn.nesc.general.core.exception.DAOException;
import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.bean.PageResult;
import com.sandrew.bury.callback.DAOCallback;
import com.sandrew.bury.callback.POCallBack;
import com.sandrew.bury.util.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by summer on 2019/7/26.
 */
@Slf4j
@Repository
public class UserManagerDAO
{
    @Resource
    SqlSessionFactory sessionFactory;

    /**
     *  分页查询用户列表
     * @param userCode
     * @param userName
     * @param userStatus
     * @param limit
     * @param curPage
     * @return
     * @throws DAOException
     */
    public PageResult<AclUserBean> userManagerPageQuery(String userCode, String userName, Integer userStatus, int limit, int curPage) throws DAOException
    {
        Parameters params = new Parameters();
        params.addParam(Constants.IF_TYPE_NO);
        params.addParam(Constants.STATUS_ENABLE);
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT\n");
        sql.append("tu.user_id,\n");
        sql.append("        tu.user_code,\n");
        sql.append("        tu.user_name,\n");
        sql.append("        tu.sex,\n");
        sql.append("        tu.user_status,\n");
        sql.append("        tu.phone,\n");
        sql.append("        tu.mobile,\n");
        sql.append("        tu.email,\n");
        sql.append("        tr.role_code,\n");
        sql.append("        group_concat(tr.role_name) AS role_name\n");
        sql.append("FROM\n");
        sql.append("tm_user tu\n");
        sql.append("LEFT JOIN tr_user_role tur ON tu.user_id = tur.user_id\n");
        sql.append("LEFT JOIN (\n");
        sql.append("    SELECT\n");
        sql.append("tm_role.role_id,\n");
        sql.append("        tm_role.role_code,\n");
        sql.append("        tm_role.role_name\n");
        sql.append("FROM\n");
        sql.append("        tm_role\n");
        sql.append("WHERE tm_role.is_delete = ?\n");
        sql.append("AND   tm_role.role_status = ?\n");
		sql.append(") tr ON tr.role_id = tur.role_id\n");
        sql.append("WHERE 1 = 1\n");
        if (StringUtils.isNotEmpty(userCode))
        {
            sql.append("AND user_code LIKE ?\n");
            params.addLikeParam(userCode);
        }
        if (StringUtils.isNotEmpty(userName))
        {
            sql.append("AND user_name LIKE ?\n");
            params.addLikeParam(userName);
        }
        if (null != userStatus)
        {
            sql.append("AND user_status = ?\n");
            params.addParam(userStatus);
        }
        sql.append("AND tu.is_delete = ?\n");
        sql.append("GROUP BY user_code,is_delete");
        params.addParam(Constants.IF_TYPE_NO);
        return session.pageQuery(sql.toString(), params.getParams(), new DAOCallback<AclUserBean>()
        {
            @Override
            public AclUserBean wrapper(ResultSet rs, int index) throws SQLException
            {

                AclUserBean userBean = new AclUserBean();
                userBean.setUserId(rs.getInt("user_id"));
                userBean.setUserCode(rs.getString("user_code"));
                userBean.setUserName(rs.getString("user_name"));
                userBean.setSex(rs.getInt("sex"));
                userBean.setPhone(rs.getString("phone"));
                userBean.setMobile(rs.getString("mobile"));
                userBean.setEmail(rs.getString("email"));
                userBean.setRoleName(rs.getString("role_name"));
                userBean.setUserStatus(rs.getString("user_status"));
                return userBean;
            }
        }, limit, curPage);
    }

    /**
     *  查询用户的角色
     * @param userId
     * @return
     * @throws DAOException
     */
    public List<TmRolePO> queryRelationRole(Integer userId) throws DAOException
    {
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder();
        sql.append("select tr.* from tm_role tr JOIN tr_user_role tur ON tr.role_id = tur.role_id WHERE tur.user_id = ?");

        Parameters params = new Parameters(userId);
        return session.select(sql.toString(), params.getParams(), new POCallBack<>(TmRolePO.class));
    }

    /**
     *  查询用户未关联的角色
     * @param userId
     * @param roleName
     * @return
     * @throws DAOException
     */
    public List<TmRolePO> getRoleExistOwn(Integer userId, String roleName) throws DAOException
    {
        Session session = sessionFactory.openSession();
        Parameters params = new Parameters(Constants.STATUS_ENABLE);
        params.addParam(userId);
        StringBuilder sql = new StringBuilder();
        sql.append("select\n");
        sql.append("tr.role_id,\n");
        sql.append("        tr.role_code,\n");
        sql.append("        tr.role_name,\n");
        sql.append("        tr.role_type,\n");
        sql.append("        tr.role_status\n");
        sql.append("from\n");
        sql.append("tm_role tr\n");
        sql.append("where\n");
        sql.append("tr.role_status = ?\n");
        sql.append("and not exists (\n");
        sql.append("    select\n");
        sql.append("    1\n");
        sql.append("    from\n");
        sql.append("    tr_user_role tur\n");
        sql.append("    where\n");
        sql.append("    tr.role_id = tur.role_id\n");
        sql.append("    and tur.user_id = ?)\n");
        if (StringUtils.isNotEmpty(roleName))
        {
            sql.append("    and tr.role_name like ?");
            params.addLikeParam(roleName);
        }
        return session.select(sql.toString(), params.getParams(), new POCallBack<>(TmRolePO.class));
    }
}

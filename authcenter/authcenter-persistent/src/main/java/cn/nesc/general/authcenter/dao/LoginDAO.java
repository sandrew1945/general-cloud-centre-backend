package cn.nesc.general.authcenter.dao;


import cn.nesc.general.authcenter.bean.RoleTreeNode;
import cn.nesc.general.authcenter.bean.UserInfo;
import cn.nesc.general.authcenter.model.TmFunctionPO;
import cn.nesc.general.authcenter.model.TmUserPO;
import cn.nesc.general.common.dictionary.Constants;
import cn.nesc.general.core.exception.DAOException;
import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.callback.DAOCallback;
import com.sandrew.bury.callback.POCallBack;
import com.sandrew.bury.util.Parameters;
import lombok.extern.slf4j.Slf4j;
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
public class LoginDAO
{
    @Resource
    SqlSessionFactory sessionFactory;

    public List<TmUserPO> selectByUserCode(String userCode, Integer userStatus) throws DAOException
    {
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from tm_user\n");
        sql.append("where user_code = ?\n");
        sql.append("and user_status = ?\n");
        sql.append("and is_delele = ?");
        Parameters params = new Parameters();
        params.addParam(userCode);
        params.addParam(userStatus);
        params.addParam(Constants.IF_TYPE_NO);
        return session.select(sql.toString(), params.getParams(), new POCallBack(TmUserPO.class));
    }

    public List<UserInfo> selectRoleByUserCode(String userCode) throws DAOException
    {
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder();
        sql.append("select tr.role_id,tr.role_code,tr.role_name from tm_user tu\n");
        sql.append("left join tr_user_role tur on tu.user_id = tur.user_id\n");
        sql.append("left join tm_role tr on tur.role_id = tr.role_id\n");
        sql.append("where tu.user_status = ?\n");
        sql.append("and tr.role_status = ?\n");
        sql.append("and tu.is_delete = ?\n");
        sql.append("and tr.is_delete = ?\n");
        sql.append("and tu.user_code = ?");
        Parameters params = new Parameters();
        params.addParam(Constants.STATUS_ENABLE);
        params.addParam(Constants.STATUS_ENABLE);
        params.addParam(Constants.IF_TYPE_NO);
        params.addParam(Constants.IF_TYPE_NO);
        params.addParam(userCode);
        return session.select(sql.toString(), params.getParams(), new POCallBack<>(UserInfo.class));
    }

    public List<RoleTreeNode> selectRoleForChoice(Integer userId) throws DAOException
    {
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder();
        sql.append("select tr.role_id as id,tr.role_name as name, 0 as pid, 'false' as isparent\n");
        sql.append("from tm_user tu\n");
        sql.append("left join tr_user_role tur on tu.user_id = tur.user_id\n");
        sql.append("left join tm_role tr on tur.role_id = tr.role_id\n");
        sql.append("where tu.user_status = ?\n");
        sql.append("and tu.is_delete = ?\n");
        sql.append("and tr.is_delete = ?\n");
        sql.append("and tr.role_status = ?\n");
        sql.append("and tu.user_id = ?");
        Parameters params = new Parameters();
        params.addParam(Constants.STATUS_ENABLE);
        params.addParam(Constants.IF_TYPE_NO);
        params.addParam(Constants.IF_TYPE_NO);
        params.addParam(Constants.STATUS_ENABLE);
        params.addParam(userId);
        return session.select(sql.toString(), params.getParams(), new DAOCallback<RoleTreeNode>()
        {
            @Override
            public RoleTreeNode wrapper(ResultSet rs, int index) throws SQLException
            {
                RoleTreeNode roleTreeNode= new RoleTreeNode();
                roleTreeNode.setId(rs.getString("id"));
                roleTreeNode.setpId(rs.getString("pid"));
                roleTreeNode.setName(rs.getString("name"));
                roleTreeNode.setIsParent(rs.getString("isparent"));
                return roleTreeNode;
            }
        });
    }

    public List<TmFunctionPO> getMenuByRole(Integer roleId)
    {
        Session session = sessionFactory.openSession();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT tf.* FROM tm_function tf join tr_role_func_front trff on tf.function_id = trff.function_id\n");
        sql.append("WHERE tf.is_delete = ?\n");
        sql.append("AND trff.role_id = ?\n");
        sql.append("ORDER BY func_order");
        Parameters params = new Parameters();
        params.addParam(Constants.IF_TYPE_NO);
        params.addParam(roleId);
        return session.select(sql.toString(), params.getParams(), new POCallBack(TmFunctionPO.class));
    }
}

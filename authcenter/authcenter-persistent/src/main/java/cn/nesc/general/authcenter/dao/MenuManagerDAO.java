package cn.nesc.general.authcenter.dao;


import cn.nesc.general.authcenter.model.TmFunctionPO;
import cn.nesc.general.common.dictionary.Constants;
import cn.nesc.general.core.exception.DAOException;
import com.sandrew.bury.Session;
import com.sandrew.bury.SqlSessionFactory;
import com.sandrew.bury.callback.POCallBack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by summer on 2019/7/26.
 */
@Slf4j
@Repository
public class MenuManagerDAO
{
    @Resource
    SqlSessionFactory sessionFactory;

    /**
     *  查询系统菜单
     * @return
     * @throws DAOException
     */
    public List<TmFunctionPO> getMenuList() throws DAOException
    {
        Session session = sessionFactory.openSession();
        TmFunctionPO cond = new TmFunctionPO();
        cond.setIsDelete(Constants.IF_TYPE_NO);
        List<TmFunctionPO> list = session.selectForOrder(cond, new POCallBack(TmFunctionPO.class), "asc","func_order");
        return list;
    }
}

package cn.nesc.general.authcenter.mapper;

import cn.nesc.general.authcenter.model.TrRoleFuncFrontPO;
import cn.nesc.general.authcenter.model.TrRoleFuncFrontPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TrRoleFuncFrontPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_role_func_front
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    long countByExample(TrRoleFuncFrontPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_role_func_front
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    int deleteByExample(TrRoleFuncFrontPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_role_func_front
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_role_func_front
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    int insert(TrRoleFuncFrontPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_role_func_front
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    int insertSelective(TrRoleFuncFrontPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_role_func_front
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    List<TrRoleFuncFrontPO> selectByExample(TrRoleFuncFrontPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_role_func_front
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    TrRoleFuncFrontPO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_role_func_front
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    int updateByExampleSelective(@Param("record") TrRoleFuncFrontPO record, @Param("example") TrRoleFuncFrontPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_role_func_front
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    int updateByExample(@Param("record") TrRoleFuncFrontPO record, @Param("example") TrRoleFuncFrontPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_role_func_front
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    int updateByPrimaryKeySelective(TrRoleFuncFrontPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tr_role_func_front
     *
     * @mbg.generated Mon Feb 27 16:12:16 CST 2023
     */
    int updateByPrimaryKey(TrRoleFuncFrontPO record);
}
package cn.nesc.general.authcenter.mapper;

import cn.nesc.general.authcenter.model.TmMenuPO;
import cn.nesc.general.authcenter.model.TmMenuPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmMenuPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_menu
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    long countByExample(TmMenuPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_menu
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int deleteByExample(TmMenuPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_menu
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int deleteByPrimaryKey(Integer menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_menu
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int insert(TmMenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_menu
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int insertSelective(TmMenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_menu
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    List<TmMenuPO> selectByExample(TmMenuPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_menu
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    TmMenuPO selectByPrimaryKey(Integer menuId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_menu
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int updateByExampleSelective(@Param("record") TmMenuPO record, @Param("example") TmMenuPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_menu
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int updateByExample(@Param("record") TmMenuPO record, @Param("example") TmMenuPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_menu
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int updateByPrimaryKeySelective(TmMenuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_menu
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int updateByPrimaryKey(TmMenuPO record);
}
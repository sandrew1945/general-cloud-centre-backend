package cn.nesc.general.authcenter.mapper;

import cn.nesc.general.authcenter.model.TmPostPO;
import cn.nesc.general.authcenter.model.TmPostPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TmPostPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_post
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    long countByExample(TmPostPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_post
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int deleteByExample(TmPostPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_post
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int deleteByPrimaryKey(Integer postId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_post
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int insert(TmPostPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_post
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int insertSelective(TmPostPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_post
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    List<TmPostPO> selectByExample(TmPostPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_post
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    TmPostPO selectByPrimaryKey(Integer postId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_post
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int updateByExampleSelective(@Param("record") TmPostPO record, @Param("example") TmPostPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_post
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int updateByExample(@Param("record") TmPostPO record, @Param("example") TmPostPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_post
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int updateByPrimaryKeySelective(TmPostPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tm_post
     *
     * @mbg.generated Wed Nov 22 15:01:10 CST 2023
     */
    int updateByPrimaryKey(TmPostPO record);
}
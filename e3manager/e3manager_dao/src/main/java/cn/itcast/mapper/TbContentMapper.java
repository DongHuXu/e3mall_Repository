package cn.itcast.mapper;

import cn.itcast.pojo.TbContent;
import cn.itcast.pojo.TbContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbContentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    long countByExample(TbContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    int deleteByExample(TbContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    int insert(TbContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    int insertSelective(TbContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    List<TbContent> selectByExampleWithBLOBs(TbContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    List<TbContent> selectByExample(TbContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    TbContent selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TbContent record, @Param("example") TbContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    int updateByExampleWithBLOBs(@Param("record") TbContent record, @Param("example") TbContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TbContent record, @Param("example") TbContentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TbContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(TbContent record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_content
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TbContent record);
}
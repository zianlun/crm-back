package zian.example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import zian.example.pojo.Activity;
import zian.example.pojo.ActivityExample;

public interface ActivityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Thu Mar 23 13:14:16 CST 2023
     */
    int countByExample(ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Thu Mar 23 13:14:16 CST 2023
     */
    int deleteByExample(ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Thu Mar 23 13:14:16 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Thu Mar 23 13:14:16 CST 2023
     */
    int insert(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Thu Mar 23 13:14:16 CST 2023
     */
    int insertSelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Thu Mar 23 13:14:16 CST 2023
     */
    List<Activity> selectByExample(ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Thu Mar 23 13:14:16 CST 2023
     */
    Activity selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Thu Mar 23 13:14:16 CST 2023
     */
    int updateByExampleSelective(@Param("record") Activity record, @Param("example") ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Thu Mar 23 13:14:16 CST 2023
     */
    int updateByExample(@Param("record") Activity record, @Param("example") ActivityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Thu Mar 23 13:14:16 CST 2023
     */
    int updateByPrimaryKeySelective(Activity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_activity
     *
     * @mbggenerated Thu Mar 23 13:14:16 CST 2023
     */
    int updateByPrimaryKey(Activity record);

    /* **
       * @MethodName
       * @Description  查询所有的活动
       * @Author ljzhang
       * @Date
       */
    List<Activity> selectAllActivity(Map<String, Object> map);

    /* **
       * @MethodName deleteActivities
       * @Description  批量删除活动
       * @Author ljzhang
       * @Date
       */
    int deleteActivities(@Param("ids") String[] ids);

}
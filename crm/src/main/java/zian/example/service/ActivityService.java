package zian.example.service;

import com.github.pagehelper.PageInfo;
import zian.example.pojo.Activity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @InterfaceName ActivityService
 * @Description
 * @Author ljzhang
 * @Date 2023/3/23 13:18
 * @Version 1.0
 */
public interface ActivityService {
    /* **
       * @MethodName saveActivity
       * @Description  创建新的市场活动
       * @Author ljzhang
       * @Date
       */
    int saveActivity(Activity activity);

    /* **
       * @MethodName queryActivityByPage
       * @Description  分页查询 @mapkey:指定返回值为map类型时哪个属性为key
       * @Author ljzhang
       * @Date
       */
    PageInfo<Activity> queryActivityByPage(Map<String, Object> map, Integer pageNumber);

    /* **
       * @MethodName deleteActivity
       * @Description
       * @Author ljzhang
       * @Date
       */
    int deleteActivityByIds(String[] ids);

    /* **
       * @MethodName
       * @Description 更新活动
       * @Author ljzhang
       * @Date
       */
    int updateActivityById(Activity activity);

    /* **
       * @MethodName
       * @Description  查询所有的活动
       * @Author ljzhang
       * @Date
       */
    List<Activity> queryAllActivity();
}

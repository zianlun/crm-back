package zian.example.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zian.example.mapper.ActivityMapper;
import zian.example.pojo.Activity;
import zian.example.pojo.ActivityExample;
import zian.example.service.ActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ActivityServiceImpl
 * @Description
 * @Author ljzhang
 * @Date 2023/3/23 13:18
 * @Version 1.0
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int saveActivity(Activity activity) {
        return activityMapper.insert(activity);
    }


    @Override
    public PageInfo<Activity> queryActivityByPage(Map<String, Object> map, Integer pageNumber) {
        ActivityExample example = new ActivityExample();
        PageHelper.startPage(pageNumber,6);
        List<Activity> activities = activityMapper.selectAllActivity(map);
        PageInfo<Activity> pageInfo = new PageInfo<Activity>(activities,7);
        return pageInfo;
    }

    @Override
    public int deleteActivityByIds(String[] ids) {
        for(int i = 0; i < ids.length; i++)
            System.out.println(ids[i]);
        return activityMapper.deleteActivities(ids);
    }

    @Override
    public int updateActivityById(Activity activity) {
        return activityMapper.updateByPrimaryKey(activity);
    }

    @Override
    public List<Activity> queryAllActivity() {
         return activityMapper.selectAllActivity(null);
    }
}

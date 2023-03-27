package zian.example.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zian.example.commons.ResponseJSON;
import zian.example.constants.StatusCode;
import zian.example.pojo.Activity;
import zian.example.service.ActivityService;
import zian.example.service.UserService;
import zian.example.utils.DateUtil;
import zian.example.utils.ExcelUtil;
import zian.example.utils.LocalDateUtil;
import zian.example.utils.UUIDUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @ClassName Activity
 * @Description
 * @Author ljzhang
 * @Date 2023/3/23 11:33
 * @Version 1.0
 */
@Controller
@ResponseBody
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @GetMapping("/allUsers")
    public Object reqAllUsers(){
        ResponseJSON responseJSON = new ResponseJSON();
        ArrayList<Object> users = userService.queryAllUsers();
        responseJSON.setCode(StatusCode.HTTPSUCCESS.getId().toString());
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("users",users);
        responseJSON.setData(data);
        return responseJSON;
    }

    @PostMapping("/createActivity")
    public Object reqCreateActivity(@RequestBody Activity activity,HttpServletRequest request){
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateUtil.formateDateTime(new Date()));
        activity.setStartDate(activity.getStartDate().substring(0,10));
        activity.setEndDate(activity.getEndDate().substring(0,10));
        ResponseJSON responseJSON = new ResponseJSON();
        responseJSON.setMessage("创建成功");
        responseJSON.setCode(StatusCode.HTTPSUCCESS.getId().toString());
        int res =  activityService.saveActivity(activity);
        if(res == 1)
            return responseJSON;
        responseJSON.setMessage("系统繁忙");
        responseJSON.setCode(StatusCode.HTTPERROR.getId().toString());
        return responseJSON;
    }

    @GetMapping("/allActivity")
    public Object reqAllActivity(@RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "owner", required = false) String owner,
                                 @RequestParam(value = "startDate",required = false) String startDate,
                                 @RequestParam(value = "endDate",required = false) String endDate,
                                 @RequestParam("pageNumber") Integer pageNumber){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("owner", owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        ResponseJSON responseJSON = new ResponseJSON();
        PageInfo<Activity> pageInfo = activityService.queryActivityByPage(map,pageNumber);
        responseJSON.setCode(StatusCode.HTTPSUCCESS.getId().toString());
        responseJSON.setData(pageInfo);
        return responseJSON;
    }

    @DeleteMapping("/deleteActivity")
    public Object reqDeleteActivity(@RequestBody String[] ids){
        ResponseJSON responseJSON = new ResponseJSON();
        responseJSON.setCode(StatusCode.HTTPSUCCESS.getId().toString());
        int result =  activityService.deleteActivityByIds(ids);
        if(result > 0)
            return responseJSON;
        responseJSON.setCode(StatusCode.HTTPERROR.getId().toString());
        return responseJSON;
    }

    @PutMapping("/updateActivity")
    public Object reqUpdateActivity(@RequestBody Activity activity){
        int result = activityService.updateActivityById(activity);
        ResponseJSON responseJSON = new ResponseJSON();
        if(result > 0){
            responseJSON.setCode(StatusCode.HTTPSUCCESS.getId().toString());
            return responseJSON;
        }
        responseJSON.setCode(StatusCode.HTTPERROR.getId().toString());
        return responseJSON;
    }

    @GetMapping("/download")
    public void reqDownload(HttpServletResponse response) throws IOException {
        List<Activity> activities = activityService.queryAllActivity();
        HSSFWorkbook workbook = ExcelUtil.getByteActivityExcel(activities);
        response.setContentType("application/octet-stream;charset=UTF-8");
        workbook.write(response.getOutputStream());
    }
}

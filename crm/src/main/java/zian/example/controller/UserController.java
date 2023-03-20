package zian.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import zian.example.commons.ResponseJSON;
import zian.example.constants.StatusCode;
import zian.example.pojo.User;
import zian.example.service.UserService;
import zian.example.utils.DateUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description
 * @Author ljzhang
 * @Date 2023/3/17 10:44
 * @Version 1.0
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /* **
       * @MethodName reqLogin
       * @Description  返回值提示密码还是账号错误 ———— email  password  message
       * @Author ljzhang
       * @Date
       */
    @GetMapping("/user/login")
    @ResponseBody
    public Object reqLogin(@RequestParam Map<String, Object> loginForm, HttpServletRequest request){
        boolean isExist = userService.verifyEmail((String)loginForm.get("email"));
        ResponseJSON responseJSON = new ResponseJSON();
        responseJSON.setCode(StatusCode.FAILE.getId().toString());
        if(!isExist){
            //账号不存在
            responseJSON.setMessage("邮箱账户不存在");
        }else{
            User user = userService.verifyLogin(loginForm);
            if(user == null){
                //密码错误
                responseJSON.setMessage("账户密码错误");
            }else{
                if(DateUtils.formateDateTime(new Date()).compareTo(user.getExpireTime()) > 0){
                    //账号过期
                    responseJSON.setMessage("邮箱账户过期，请申诉");
                }else if("0".equals(user.getLockState())){
                    //账号锁定
                    responseJSON.setMessage("邮箱账户锁定");
                }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                    //登录ip不满足条件 ---- 以后再考虑此业务
                    responseJSON.setCode(StatusCode.SUCCESS.getId().toString());
                    responseJSON.setMessage("登录成功！");
                    //返回用户的个人信息
                }else{
                    responseJSON.setCode(StatusCode.SUCCESS.getId().toString());
                    responseJSON.setMessage("登录成功！");
                }
            }
        }
        return responseJSON;
    }
}

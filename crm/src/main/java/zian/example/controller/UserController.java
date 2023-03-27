package zian.example.controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zian.example.commons.ResponseJSON;
import zian.example.constants.StatusCode;
import zian.example.pojo.User;
import zian.example.service.UserService;
import zian.example.utils.DateUtil;
import zian.example.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
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
            User  user = userService.verifyLogin(loginForm);
            if(user == null){
                //密码错误
                responseJSON.setMessage("账户密码错误");
            }else{
                if(DateUtil.formateDateTime(new Date()).compareTo(user.getExpireTime()) > 0){
                    //账号过期
                    responseJSON.setMessage("邮箱账户过期，请申诉");
                }else if("0".equals(user.getLockState())){
                    //账号锁定
                    responseJSON.setMessage("邮箱账户锁定");
                }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                    //登录ip不满足条件 ---- 以后再考虑此业务
                    responseJSON.setCode(StatusCode.SUCCESS.getId().toString());
                    responseJSON.setMessage("登录成功！");
                    //设置token
                    Map<String,Object> data = new HashMap<String, Object>();
                    data.put("access", TokenUtil.accessSign((String)loginForm.get("email"),(String)loginForm.get("password"),"user"));
                    data.put("refresh", TokenUtil.refreshSign((String)loginForm.get("email"),(String)loginForm.get("password"),"user"));
                    responseJSON.setData(data);
                }else{
                    responseJSON.setCode(StatusCode.SUCCESS.getId().toString());
                    responseJSON.setMessage("登录成功！");
                    //设置token
                    Map<String,Object> data = new HashMap<String, Object>();
                    data.put("access", TokenUtil.accessSign((String)loginForm.get("email"),(String)loginForm.get("password"),"user"));
                    data.put("refresh", TokenUtil.refreshSign((String)loginForm.get("email"),(String)loginForm.get("password"),"user"));
                    responseJSON.setData(data);
                }
            }
        }
        return responseJSON;
    }

    @PostMapping("/user/loginOut")
    @ResponseBody
    public Object reqLoginOut(){
        ResponseJSON responseJSON = new ResponseJSON();
        responseJSON.setCode(StatusCode.HTTPSUCCESS.getId().toString());
        return responseJSON;
    }

    @GetMapping("/user/getInfo")
    @ResponseBody
    public Object reqGetInfo(HttpServletRequest request){
        String token = request.getHeader("access");
        Claims claims;
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            claims = TokenUtil.pareseJWT(token);
            responseJSON.setCode(StatusCode.HTTPSUCCESS.getId().toString());
            responseJSON.setMessage("请求成功");
            responseJSON.setData(userService.queryUserInfo(claims.get("username",String.class),claims.get("password",String.class)));
        }catch (Exception error){
            responseJSON.setCode(StatusCode.HTTPUNAUTHORIZED.getId().toString());
            responseJSON.setMessage("请求超时，请重新登录");
            return responseJSON;
        }
        return responseJSON;
    }

    @GetMapping("/jwt/accessStatus")
    @ResponseBody
    public void reqAccessStatus(HttpServletRequest request, HttpServletResponse response){
        String access = request.getHeader("access");
        Claims claims;
        ResponseJSON responseJSON = new ResponseJSON();
        try {
            claims = TokenUtil.pareseJWT(access);
            response.setStatus(200);
        }catch (Exception error){
            /*
            未授权  ---- 让前端获取新的token
            * */
            response.setStatus(401);
        }
    }

    @GetMapping("/jwt/refreshToken")
    @ResponseBody
    public ResponseJSON reqRefreshAccess(HttpServletRequest request){
        String username =  (String)request.getAttribute("username");
        String password =  (String)request.getAttribute("password");
        String audience =  (String)request.getAttribute("audience");
        System.out.println(username);
        ResponseJSON responseJSON = new ResponseJSON();
        User user =  userService.verifyLogin(username, password);
        if(user != null){
            //设置token
            Map<String,Object> data = new HashMap<String, Object>();
            data.put("access", TokenUtil.accessSign(username,password,audience));
            responseJSON.setData(data);
        }
        return responseJSON;
    }


}

package zian.example.service;

import zian.example.pojo.User;
import zian.example.pojo.UserInfo;

import java.util.ArrayList;
import java.util.Map;

/**
 * @InterfaceName UserService
 * @Description
 * @Author ljzhang
 * @Date 2023/3/16 22:31
 * @Version 1.0
 */

public interface UserService {
    /* **
       * @MethodName
       * @Description  邮箱账号是否正确
       * @Author ljzhang
       * @Date
       */
      boolean verifyEmail(String email);

      /* **
         * @MethodName
         * @Description  验证登录信息
         * @Author ljzhang
         * @Date
         */
      User verifyLogin(Map<String,Object> loginForm);

      /* **
         * @MethodName verifyLogin
         * @Description  登录验证重载方法
         * @Author ljzhang
         * @Date
         */
      User verifyLogin(String email, String password);

      /* **
         * @MethodName queryUserInfo
         * @Description  查询用户的信息
         * @Author ljzhang
         * @Date
         */
      Map<String, Object> queryUserInfo(String email, String password);


      /* **
         * @MethodName queryAllUsers
         * @Description  查询所用用户
         * @Author ljzhang
         * @Date
         */
      ArrayList<Object> queryAllUsers();
}

package zian.example.service;

import zian.example.pojo.User;

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
}

package zian.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import zian.example.mapper.UserMapper;
import zian.example.pojo.User;
import zian.example.pojo.UserExample;
import zian.example.service.UserService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description
 * @Author ljzhang
 * @Date 2023/3/16 22:31
 * @Version 1.0
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean verifyEmail(String email) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(email);
        List<User> users = userMapper.selectByExample(userExample);
        return !users.isEmpty();
    }

    /* **
       * @MethodName verifyLogin
       * @Description  存在用户返回true 不存在返回false
       * @Author ljzhang
       * @Date
       */
    @Override
    public User verifyLogin(Map<String,Object> loginForm) {
        User user = userMapper.selectByEmailAndPassword(loginForm);
        return user;
    }
}

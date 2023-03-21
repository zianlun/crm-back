package zian.example.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zian.example.mapper.UserInfoMapper;
import zian.example.mapper.UserMapper;
import zian.example.pojo.UserExample;
import zian.example.service.UserService;
import zian.example.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserTest
 * @Description
 * @Author ljzhang
 * @Date 2023/3/16 22:32
 * @Version 1.0
 */
public class UserTest {
    @Test
    public void test1(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("email","ls@163.com");
        map.put("password","yf123");
        System.out.println(userMapper.selectByEmailAndPassword(map));
    }

    @Test
    public void test2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("email","ls@163.com");
        map.put("password","yf123");
    }

    @Test
    public void test3(){
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
        System.out.println(userInfoMapper.selectById("06f5fc056eac41558a964f96daa7f27c"));
    }
}

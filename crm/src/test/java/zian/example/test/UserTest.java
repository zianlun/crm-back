package zian.example.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import zian.example.mapper.ActivityMapper;
import zian.example.mapper.UserInfoMapper;
import zian.example.mapper.UserMapper;
import zian.example.pojo.Activity;
import zian.example.pojo.ActivityExample;
import zian.example.pojo.UserExample;
import zian.example.service.ActivityService;
import zian.example.service.UserService;
import zian.example.utils.SqlSessionUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
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
        ActivityExample example = new ActivityExample();
        ActivityMapper activityMapper = sqlSession.getMapper(ActivityMapper.class);
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

    @Test
    public void test4(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(userService.queryAllUsers());
    }

    @Test
    public void test5(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE;
        System.out.println(dateTimeFormatter.format(LocalDateTime.now()));
    }

    @Test
    public void test6(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        ActivityService activityService = applicationContext.getBean(ActivityService.class);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name","传单");
        System.out.println(activityService.queryActivityByPage(map,1));
    }
}

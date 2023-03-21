package zian.example.interceptor;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import zian.example.constants.StatusCode;
import zian.example.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName verifyUser
 * @Description
 * @Author ljzhang
 * @Date 2023/3/21 12:29
 * @Version 1.0
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String access = request.getHeader("access");

        String refresh = request.getHeader("refresh");

        Claims accessClaims = null, refreshClaims = null;

        Date access_exp = null, refresh_exp = null;

        Map<String, Object> data = new HashMap<String, Object>();

        try {
            accessClaims = TokenUtil.pareseJWT(access);
            String username = accessClaims.get("username", String.class);
            String password = accessClaims.get("password", String.class);
            access_exp = accessClaims.getExpiration();
            request.setAttribute("username",username);
            request.setAttribute("password",password);
            return true;
        } catch (Exception e) {
//            e.printStackTrace();
            try {
                refreshClaims = TokenUtil.pareseJWT(refresh);
                refresh_exp = refreshClaims.getExpiration();
                String username = refreshClaims.get("username", String.class);
                String password = refreshClaims.get("password", String.class);
                String audience = refreshClaims.get("audience", String.class);
                request.setAttribute("username",username);
                request.setAttribute("password",password);
                request.setAttribute("audience",audience);
//                System.out.println(request.getRequestURL().toString().equals("http://10.21.24.193:8000/crm/jwt/refreshToken"));
                if(request.getRequestURL().toString().equals("http://10.21.24.193:8000/crm/jwt/refreshToken")){
                    response.setStatus(StatusCode.HTTPSUCCESS.getId());
                    return true;
                }
            } catch (Exception exception) {
//                exception.printStackTrace();
                data.put("code",StatusCode.HTTPFORBIDDEN.getId());
                PrintWriter writer = null;
                writer = response.getWriter();
                writer.write(JSON.toJSONString(data));
                return false;
            }
        }
        PrintWriter writer = null;
        data.put("code",StatusCode.HTTPUNAUTHORIZED.getId());
        writer = response.getWriter();
        writer.write(JSON.toJSONString(data));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

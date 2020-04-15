package life.xiaoyang.community.component;

import life.xiaoyang.community.bean.User;
import life.xiaoyang.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

@Component
public class UserInteceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean pass = false;
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; ++i) {
                if (cookies[i].getName().equals("token")) {
                    String value = cookies[i].getValue();
                    user = userMapper.findUserByToken(value);
                    break;
                } else if (i == cookies.length - 1) {
                    pass = true;
                }
            }
        }
        if (cookies == null || pass == true) {
            return true;
        }
        if (user != null) {
            request.getSession().setAttribute("user", user);
            return true;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

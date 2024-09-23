package by.tms.onlinerclonec29onl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("account") == null && (
                request.getRequestURL().toString().contains("profile") ||
                request.getRequestURL().toString().contains("logout"))) {
            response.sendRedirect("/");
            return false;
        }
        if (request.getSession().getAttribute("account") != null && (
                request.getRequestURL().toString().contains("registration") ||
                        request.getRequestURL().toString().contains("login"))) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }


}

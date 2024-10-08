package be.springboot.pp.authentication.interceptors;

import be.springboot.pp.authentication.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("AuthInterceptor: preHandle");
        String token = request.getHeader("token");
        log.error("AuthInterceptor: token: {}", token);

        if (token == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String username = jwtUtils.getUsernameFromToken(token);
        log.error("AuthInterceptor: username: {}", username);

        boolean result = jwtUtils.validateToken(token);
        log.error("AuthInterceptor: result: {}", result);

        if (!result) {
            response.getWriter().write("Invalid token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }

}

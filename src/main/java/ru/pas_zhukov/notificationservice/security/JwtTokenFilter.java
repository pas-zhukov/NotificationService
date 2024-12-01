package ru.pas_zhukov.notificationservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.pas_zhukov.notificationservice.model.User;
import ru.pas_zhukov.notificationservice.model.UserRole;


import java.io.IOException;
import java.util.List;

/**
 * Фильтр для применения в {@code SecurityFilterChain}.
 * Проверяет наличие корректного JWT-токена в заголовке запроса
 */
@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenManager jwtTokenManager;

    public JwtTokenFilter(JwtTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = authorizationHeader.substring(7);
        String loginFromToken;
        UserRole authorityFromToken;
        try {
            loginFromToken = jwtTokenManager.getLoginFromToken(jwtToken);
            authorityFromToken = jwtTokenManager.getUserRoleFromToken(jwtToken);
        } catch (Exception e) {
            logger.error("Error while reading JWT", e);
            filterChain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new User(authorityFromToken, loginFromToken),
                null,
                List.of(new SimpleGrantedAuthority(authorityFromToken.toString()))
        );
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);
    }
}

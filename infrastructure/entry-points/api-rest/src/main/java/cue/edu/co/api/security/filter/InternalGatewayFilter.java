package cue.edu.co.api.security.filter;


import cue.edu.co.api.security.constant.SecurityConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class InternalGatewayFilter extends OncePerRequestFilter {

    @Value("${app.internal.secret}")
    private String internalSecret;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String headerSecret = request.getHeader(SecurityConstant.GATEWAY_INTERNAL_HEADER);
        if (headerSecret == null || !headerSecret.equals(internalSecret)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(SecurityConstant.ERROR_UNAUTHORIZED_GATEWAY);
            return;
        }

        String userId = request.getHeader(SecurityConstant.USER_ID_HEADER);
        String userRole = request.getHeader(SecurityConstant.USER_ROLE_HEADER);

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            List<GrantedAuthority> authorities = (userRole != null)
                    ? List.of(new SimpleGrantedAuthority("ROLE_" + userRole))
                    : List.of();

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userId, null, authorities);

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}

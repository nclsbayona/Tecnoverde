package co.edu.javeriana.ms.LoadBalancer.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import co.edu.javeriana.ms.LoadBalancer.services.JWTService;
import co.edu.javeriana.ms.LoadBalancer.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header_auth = request.getHeader("Authorization");
        String user=null;
        String token = null;
        if (header_auth != null && header_auth.startsWith("Bearer ")) {
            token = header_auth.substring(7);
            System.out.println(token);
            user = jwtService.extractUsername(token);
        }
        if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails ud = userService.loadUserByUsername(user);
            if (jwtService.validateToken(token, ud)) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud, null,
                        ud.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
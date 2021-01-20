package com.example.demo8.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt ="eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJuaW5pIiwiaWF0IjoxNjExMTQ1NzU0LCJleHAiOjE2MTExNDY2NTV9.NQ1NzXDqxRxZDjTbDZkL4hNK9zE6GQPw-yLK_J4DjF8tCeFk8-EklJA6NwGhn__3G0cUKBjqAwEa_YLtTLyNbDu2POxh94WspNv5Fr5pD07f-I8urXPF6gAi5NlJ5qwmVZcRtPnI9tomSknN0eoURlIDo9zpKmsOMN4QjuCDbAN49s8KU8jVsJrqQfOVB492c9ArQ_aoJwv2DTpuEw-KEQqgf02qbh1e0seGFydGBSlnZqoswIi5fT6P-xOboiXFHYvXx1g_j8EefRZKTRf5dKmaB2Yy-1adHE7P9YrYKvP2BBcQfDPRsybwtOtfTTVGS5MlQ2oybNVwDYnlI--jfw";//getJwtFromRequest(request);

        if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
            String username = jwtProvider.getUsernameFromJWT(jwt);
            System.out.println(jwt);

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return bearerToken;
    }

}

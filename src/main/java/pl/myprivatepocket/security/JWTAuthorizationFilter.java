package pl.myprivatepocket.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.myprivatepocket.utils.TokenUtils;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private TokenUtils tokenUtils;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, TokenUtils tokenUtils) {
        super(authenticationManager);
        this.tokenUtils = tokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        System.out.println(tokenUtils.getJwtHeader());
        String header = req.getHeader(tokenUtils.getJwtHeader());

        if (header == null || !header.startsWith(tokenUtils.getJwtPrefix())) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(tokenUtils.getJwtHeader());
        if (token != null) {
            String user = tokenUtils.getSubjectFromToken(token);

            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
        }
        return null;
    }
}
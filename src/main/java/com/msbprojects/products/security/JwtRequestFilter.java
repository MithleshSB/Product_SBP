package com.msbprojects.products.security;


import com.msbprojects.products.service.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


/*
In a JWT-based authentication system,
a Request Filter (usually a Spring Security OncePerRequestFilter) plays a crucial role in handling incoming requests.
Its primary purpose is to intercept each request before it reaches the controller and check for a valid JWT token.

1️⃣ Intercept Requests: It checks every request before reaching the application to determine if a JWT token is present.
2️⃣ Validate Token: It ensures the token is properly signed, not expired, and belongs to a legitimate user.
3️⃣ Extract User Details: It retrieves user information from the token and sets up authentication in Spring Security.
4️⃣ Secure Endpoints: It blocks unauthorized requests and allows only valid users to access protected resources.
also can manipulate the response before sending it back to the client. Here’s how:
1️⃣ Modify Response Body – You can change JSON, XML, or text output before it's returned to the user.
2️⃣ Set Custom Headers – You can add, remove, or modify HTTP headers for security or tracking purposes.
3️⃣ Change Status Codes – You can alter HTTP status codes (e.g., return 403 Forbidden instead of 401 Unauthorized).
4️⃣ Send Custom Error Messages – You can catch JWT errors and return meaningful custom error responses.

 */
@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Remove "Bearer " prefix
            username = jwtUtil.extractUsername(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = myUserDetailService.loadUserByUsername(username);
                List<String> roles = jwtUtil.extractRole(token);
                List<SimpleGrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(roles.toString())).toList();
                if (jwtUtil.validateToken(token, userDetails.getUsername())) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, authorities);
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
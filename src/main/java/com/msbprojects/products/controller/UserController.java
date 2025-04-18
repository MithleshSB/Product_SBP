package com.msbprojects.products.controller;

import com.msbprojects.products.dto.UserDTO;
import com.msbprojects.products.entity.User;
import com.msbprojects.products.security.JWTUtil;
import com.msbprojects.products.security.SecurityConfig;
import com.msbprojects.products.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return myUserDetailService.createUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO){
        //authentication manager will authenticate the credentials via authentication provider
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        //security config has its context of all the methods it has,
        // now we will set the authentication to security context
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        //gettting the assigned roles
        List<String> roles = authenticate.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).toList();
        System.out.println("ROLES LISTED --> "+ roles);

        UserDetails userDetails = myUserDetailService.loadUserByUsername(userDTO.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername(),roles);

    }

}

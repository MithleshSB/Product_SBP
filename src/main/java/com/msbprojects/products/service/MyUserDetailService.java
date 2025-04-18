package com.msbprojects.products.service;

import com.msbprojects.products.entity.User;
import com.msbprojects.products.repository.UserRepository;
import com.msbprojects.products.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        //encoding the password before registering the user to DB.
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        return new UserPrinciple(byUsername.get());
    }

}

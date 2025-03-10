package com.myspringapp.service;

import com.myspringapp.config.MyUserDetails;
import com.myspringapp.entity.User;
import com.myspringapp.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailService implements UserDetailsService {
    private final UserRepo userRepo;
    public MyUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByName(username);

        return user.map(MyUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException(username + "doesn't exists in repo"));



    }
}

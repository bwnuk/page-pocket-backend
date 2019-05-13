package com.cracow.service.security;

import com.cracow.entity.UserEntity;
import com.cracow.error.common.NotFoundProblem;
import com.cracow.repository.UserRepository;
import com.google.common.collect.ImmutableSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final String userRole = "ROLE_USER";
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(
            UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new NotFoundProblem("user", "email", email));

        Set<GrantedAuthority> grantedAuthorities = ImmutableSet.of(new SimpleGrantedAuthority(userRole));

        return new User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
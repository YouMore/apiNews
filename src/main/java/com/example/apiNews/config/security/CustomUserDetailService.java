package com.example.apiNews.config.security;

import com.example.apiNews.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByLogin(username).orElse(null);
        return UserPrincipal.builder()
                .userId(user.getId())
                .email(user.getLogin())
                .authorities(List.of(new SimpleGrantedAuthority(user.getRole().toString())))
                .password(user.getPassword())
                .build();
    }
}

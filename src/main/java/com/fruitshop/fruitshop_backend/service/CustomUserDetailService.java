package com.fruitshop.fruitshop_backend.service;

import com.fruitshop.fruitshop_backend.dto.UserDto;
import com.fruitshop.fruitshop_backend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserDto userDto = userMapper.findByUsername(username);
        if(userDto == null){
            throw new UsernameNotFoundException("찾을 수 없는 username : " + username);
        }

        List<GrantedAuthority> authorities = userDto.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.isEnabled(),
                true, true, true,authorities
        );
    }
}

package com.fruitshop.fruitshop_backend.service;

import com.fruitshop.fruitshop_backend.dto.UserDto;
import com.fruitshop.fruitshop_backend.mapper.APIUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class APIUserService {
    private final APIUserMapper apiUserMapper;
    private final PasswordEncoder passwordEncoder;

    public void signup(UserDto userDto){
        String encodePw = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodePw);
        userDto.setEnabled(userDto.isEnabled());
        // 사용자 등록
        apiUserMapper.save(userDto);
        // 권한 등록
        apiUserMapper.insertUserRole(userDto.getId(),1);
    }
}

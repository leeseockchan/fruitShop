package com.fruitshop.fruitshop_backend.service;

import com.fruitshop.fruitshop_backend.dto.UserDto;
import com.fruitshop.fruitshop_backend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void signup(UserDto userDto){
        userDto.setEnabled(true);

        userMapper.save(userDto);
    }

}

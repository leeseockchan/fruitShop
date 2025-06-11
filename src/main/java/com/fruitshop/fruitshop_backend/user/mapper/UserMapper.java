package com.fruitshop.fruitshop_backend.user.mapper;

import com.fruitshop.fruitshop_backend.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void save(UserDto userDto);

}

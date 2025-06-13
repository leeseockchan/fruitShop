package com.fruitshop.fruitshop_backend.mapper;

import com.fruitshop.fruitshop_backend.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void save(UserDto userDto);

}

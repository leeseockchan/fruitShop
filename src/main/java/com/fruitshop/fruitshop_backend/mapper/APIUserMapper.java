package com.fruitshop.fruitshop_backend.mapper;

import com.fruitshop.fruitshop_backend.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface APIUserMapper {
    void save(UserDto userDto);
    void insertUserRole(@Param("userId") int userId, @Param("roleId") int roleId);
}

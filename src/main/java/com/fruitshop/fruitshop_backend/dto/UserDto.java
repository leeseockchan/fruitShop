package com.fruitshop.fruitshop_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private boolean enabled;
    private List<RoleDto> roles;
}

package com.fruitshop.fruitshop_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean enabled = true;
    private List<RoleDto> roles;
}

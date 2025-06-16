package com.fruitshop.fruitshop_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private LocalDateTime create_at;
    private LocalDateTime update_at;
    private boolean enabled;
}

package com.fruitshop.fruitshop_backend.controller;

import com.fruitshop.fruitshop_backend.dto.UserDto;
import com.fruitshop.fruitshop_backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/auth/signup")
    public String signup(){
        return "/user/signup";
    }

    @PostMapping("/auth/signup")
    public String signup(@ModelAttribute UserDto userDto){
        userService.signup(userDto);
        return "/auth/login";
    }

    @GetMapping("/auth/login")
    public String login(){
        return "/user/login";
    }
}

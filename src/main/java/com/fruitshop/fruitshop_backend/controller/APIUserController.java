package com.fruitshop.fruitshop_backend.controller;

import com.fruitshop.fruitshop_backend.dto.UserDto;
import com.fruitshop.fruitshop_backend.service.APIUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class APIUserController {
    private final APIUserService apiUserService;

    @PostMapping("/api/v1/auth/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto userDto){
        apiUserService.signup(userDto);
        return ResponseEntity.ok("로그인에 성공하였습니다.");
    }
}

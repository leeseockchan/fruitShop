package com.fruitshop.fruitshop_backend.controller;

import com.fruitshop.fruitshop_backend.component.JwtUtil;
import com.fruitshop.fruitshop_backend.dto.UserDto;
import com.fruitshop.fruitshop_backend.service.APIUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class APIUserController {
    private final APIUserService apiUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto userDto){
        apiUserService.signup(userDto);
        return ResponseEntity.ok("회원 가입에 성공하였습니다.");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> user) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.get("username"), user.get("password"))
            );

            String token = jwtUtil.generateToken(authentication.getName());
            return Map.of("token", token);

        } catch (AuthenticationException e) {
            throw new RuntimeException("자격 증명이 잘못 되었습니다.");
        }
    }
}

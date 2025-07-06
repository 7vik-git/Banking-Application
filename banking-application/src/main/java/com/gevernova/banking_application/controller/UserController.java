package com.gevernova.banking_application.controller;

import com.gevernova.banking_application.dto.RegisterUserApiResponse;
import com.gevernova.banking_application.dto.UserRequestDTO;
import com.gevernova.banking_application.dto.UserResponseDTO;
import com.gevernova.banking_application.dto.VerifyUserDTO;
import com.gevernova.banking_application.service.UserService;
import com.gevernova.banking_application.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterUserApiResponse<UserResponseDTO>> registerUser(@Valid @RequestBody UserRequestDTO dto){
        UserResponseDTO rdto = userService.registerUser(dto);
        //using this to indicate user to complete verification
        //created sep dto class, so that both custom message asking to verify and rdto can be sent as response
        RegisterUserApiResponse<UserResponseDTO> response =
                new RegisterUserApiResponse<>
                        ("Account Created Successfully. Verify with OTP to activate your account", rdto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestBody VerifyUserDTO dto){
        boolean response = userService.verifyUser(dto);
        if(response){
            return ResponseEntity.ok("OTP verification successful");
        }
        return ResponseEntity.badRequest().body("OTP & Email mismatch");
    }
}

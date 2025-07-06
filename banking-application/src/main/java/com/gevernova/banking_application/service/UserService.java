package com.gevernova.banking_application.service;

import com.gevernova.banking_application.dto.UserRequestDTO;
import com.gevernova.banking_application.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO registerUser(UserRequestDTO user);
//    void generateOTP(String email);
}

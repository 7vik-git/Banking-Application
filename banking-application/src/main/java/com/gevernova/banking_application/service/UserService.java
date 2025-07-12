package com.gevernova.banking_application.service;

import com.gevernova.banking_application.dto.UserRequestDTO;
import com.gevernova.banking_application.dto.UserResponseDTO;
import com.gevernova.banking_application.dto.VerifyUserDTO;

public interface UserService {
    UserResponseDTO registerUser(UserRequestDTO user);
    public boolean verifyUser(VerifyUserDTO dto);
}

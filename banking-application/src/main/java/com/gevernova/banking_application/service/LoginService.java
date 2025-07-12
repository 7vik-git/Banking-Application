package com.gevernova.banking_application.service;

import com.gevernova.banking_application.dto.UserLoginRequestDTO;
import com.gevernova.banking_application.dto.UserLoginResponseDTO;

public interface LoginService {
    public UserLoginResponseDTO userLogin(UserLoginRequestDTO dto);

}

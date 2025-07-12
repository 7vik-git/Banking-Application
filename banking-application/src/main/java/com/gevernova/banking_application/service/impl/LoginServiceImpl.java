package com.gevernova.banking_application.service.impl;

import com.gevernova.banking_application.dto.UserLoginRequestDTO;
import com.gevernova.banking_application.dto.UserLoginResponseDTO;
import com.gevernova.banking_application.entity.User;
import com.gevernova.banking_application.exceptions.AccountNotFoundException;
import com.gevernova.banking_application.exceptions.UserNotVerifiedException;
import com.gevernova.banking_application.exceptions.WrongPasswordException;
import com.gevernova.banking_application.repository.UserRepository;
import com.gevernova.banking_application.security.CustomUserDetails;
import com.gevernova.banking_application.security.JwtUtil;
import com.gevernova.banking_application.service.LoginService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserLoginResponseDTO userLogin(UserLoginRequestDTO dto){
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(()->new AccountNotFoundException("Invalid UserId"));

        if(!passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            throw new WrongPasswordException("Invalid Password, Enter correct password again");
        }

        if(!user.isVerified()) throw new UserNotVerifiedException("Please verify your account first");
        log.info("Credentials matched successfully & user is active and verified");
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String token = jwtUtil.generateToken(userDetails);
        log.info("JWT token generated successfully");
        return new UserLoginResponseDTO(token);
    }

}

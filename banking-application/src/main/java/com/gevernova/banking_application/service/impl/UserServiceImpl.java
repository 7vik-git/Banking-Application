package com.gevernova.banking_application.service.impl;

import com.gevernova.banking_application.dto.UserRequestDTO;
import com.gevernova.banking_application.dto.UserResponseDTO;
import com.gevernova.banking_application.dto.VerifyUserDTO;
import com.gevernova.banking_application.entity.OtpDetails;
import com.gevernova.banking_application.entity.User;
import com.gevernova.banking_application.entity.enums.Purpose;
import com.gevernova.banking_application.exceptions.AccountNotFoundException;
import com.gevernova.banking_application.exceptions.EmailIdAlreadyExistsException;
import com.gevernova.banking_application.exceptions.UserNameNotAvailableException;
import com.gevernova.banking_application.mapper.UserMapper;
import com.gevernova.banking_application.repository.EmailVerificationRepository;
import com.gevernova.banking_application.repository.UserRepository;
import com.gevernova.banking_application.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpServiceImpl otpService;


    @Override
    public UserResponseDTO registerUser(UserRequestDTO dto) {
        if(userRepository.findByUsername(dto.getUsername()).isPresent()){
            throw new UserNameNotAvailableException("Sorry Username already taken");
        }
        if(userRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new EmailIdAlreadyExistsException("An account with this Email already exists!");
        }
        User user = UserMapper.dtoToUser(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("User Registered Successfully");
        otpService.generateOTP(user.getEmail(), Purpose.REGISTER);
        log.info("email verification code sent to the user");
        return UserMapper.userToResponseDTO(user);
    }

    public boolean verifyUser(VerifyUserDTO dto){
        boolean response = otpService.validateOtp(dto.getEmail(), dto.getOtp());
        if(response){
            User user = userRepository.findByEmail(dto.getEmail())
                    .orElseThrow(()->new AccountNotFoundException("Mail id not found"));
            user.setVerified(true);
            log.info("OTP Verification success");
            return true;
        }
        return false;
    }

}

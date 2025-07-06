package com.gevernova.banking_application.service;

import com.gevernova.banking_application.dto.VerifyUserDTO;
import com.gevernova.banking_application.entity.enums.Purpose;

public interface OtpService {
    void sendOtpToEmail(String toEmail, String otp);
    boolean validateOtp(String email,String otp);
    void generateOTP(String email, Purpose purpose);
}

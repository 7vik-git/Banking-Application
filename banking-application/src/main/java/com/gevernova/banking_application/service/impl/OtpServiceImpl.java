package com.gevernova.banking_application.service.impl;

import com.gevernova.banking_application.entity.OtpDetails;
import com.gevernova.banking_application.entity.User;
import com.gevernova.banking_application.entity.enums.Purpose;
import com.gevernova.banking_application.exceptions.AccountNotFoundException;
import com.gevernova.banking_application.exceptions.OtpExpiredException;
import com.gevernova.banking_application.exceptions.OtpNotFoundException;
import com.gevernova.banking_application.repository.OtpDetailsRepository;
import com.gevernova.banking_application.repository.UserRepository;
import com.gevernova.banking_application.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@RequiredArgsConstructor
@Slf4j
@Service
public class OtpServiceImpl implements OtpService {
    private final UserRepository userRepository;
    private final JavaMailSender mailSender;
    private final OtpDetailsRepository otpDetailsRepository;

    public void generateOTP(String email, Purpose purpose){
        String otp = String.format("%06d", new Random().nextInt(999999));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException("User not found with email: " + email));

        OtpDetails token = new OtpDetails();
        token.setEmail(email);
        token.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        token.setOtp(otp);
        token.setPurpose(purpose);
        token.setUser(user);
        sendOtpToEmail(email,otp);
        otpDetailsRepository.save(token);
        log.info("OTP Generated successfully");
    }

    @Override
    public void sendOtpToEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Verify your Email");
        message.setText("Your verification OTP is: " + otp);
        mailSender.send(message);
        log.info("OTP sent to mail successfully");
    }

    public boolean validateOtp(String email,String otp){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new AccountNotFoundException("Enter correct Email"));
        OtpDetails otpD = otpDetailsRepository.findByUserIdAndOtp(user.getId(), otp)
                .orElseThrow(()->new OtpNotFoundException("OTP not requested or incorrect"));
        if(otpD.getExpiryTime().isBefore(LocalDateTime.now())){
            throw new OtpExpiredException("OTP Expired, request for a new OTP");
        }
        log.info("OTP validation successful");
        user.setVerified(true);
        userRepository.save(user);
        log.info("User is now verified");
        return true;
    }

}

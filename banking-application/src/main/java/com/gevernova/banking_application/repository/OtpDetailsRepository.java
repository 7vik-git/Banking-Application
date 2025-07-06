package com.gevernova.banking_application.repository;

import com.gevernova.banking_application.entity.OtpDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpDetailsRepository extends JpaRepository<OtpDetails,Long> {
    Optional<OtpDetails> findByUserIdAndOtp(Long userId, String otp);
}

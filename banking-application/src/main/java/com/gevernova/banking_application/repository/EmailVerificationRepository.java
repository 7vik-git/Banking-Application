package com.gevernova.banking_application.repository;

import com.gevernova.banking_application.entity.OtpDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<OtpDetails, Long> {
    Optional<OtpDetails> findByEmail(String email);
}

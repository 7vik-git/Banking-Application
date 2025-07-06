package com.gevernova.banking_application.entity;

import com.gevernova.banking_application.entity.enums.Purpose;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OtpDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String otp;
    private LocalDateTime expiryTime;
    private Purpose purpose;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

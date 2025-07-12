package com.gevernova.banking_application.entity;

import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "email")
    private String email;
    private boolean isVerified = false;

}

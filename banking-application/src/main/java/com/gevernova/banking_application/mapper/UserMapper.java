package com.gevernova.banking_application.mapper;

import com.gevernova.banking_application.dto.UserRequestDTO;
import com.gevernova.banking_application.dto.UserResponseDTO;
import com.gevernova.banking_application.entity.User;

public class UserMapper {
    public static User dtoToUser(UserRequestDTO dto){
        return User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }

    public static UserRequestDTO userToDTO(User user){
        return UserRequestDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .build();
    }

    public static UserResponseDTO userToResponseDTO(User user){
        return UserResponseDTO.builder()
                .username(user.getUsername())
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}

package com.userService.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    private String name;

    private String surname;
    
    private String document;

    private Long userId;

    private String documentType;
}

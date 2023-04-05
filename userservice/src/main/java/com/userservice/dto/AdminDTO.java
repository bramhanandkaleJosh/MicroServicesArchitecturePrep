package com.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

    private String name;

    private String surname;
    
    private String document;

    private Long userId;

    private String documentType;
}

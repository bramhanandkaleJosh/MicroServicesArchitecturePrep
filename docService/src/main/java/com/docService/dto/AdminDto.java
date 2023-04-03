package com.docService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminDto {

    private String name;

    private String surname;
    
    private String document;

    private String documentType;

    private Long userId;
}

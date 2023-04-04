package com.adminService.adminService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminRequest {
        private String firstName;
        private String lastName;
        private String email;
        private long userId;
        private long contactNumber;
        private String docName;
        private String docNumber;
}

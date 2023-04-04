package com.userService.userService.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
    private String status;
    private Integer statusCode;
    private JsonNode data;
    private String message;
}

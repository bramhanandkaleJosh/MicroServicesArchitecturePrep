package com.docService.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO {

    private String status;

    private Integer statusCode;

    private JsonNode data;

    private String message;

}

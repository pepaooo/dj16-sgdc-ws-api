package com.sgdc.core.ws.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private Integer status;
    private String error;
    private String detail;
    private String path;
    private LocalDateTime timestamp;

}

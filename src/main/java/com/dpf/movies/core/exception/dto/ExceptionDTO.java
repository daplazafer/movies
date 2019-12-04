package com.dpf.movies.core.exception.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionDTO {

    private LocalDateTime timestamp;

    private String message;

}

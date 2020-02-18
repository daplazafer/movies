package com.dpf.movies.common.exception.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionDTO {

    private LocalDateTime timestamp;

    private String message;

}

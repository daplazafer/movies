package com.dpf.movies.core.exception.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class ExceptionDTO {

	private LocalDateTime timestamp;

	private String message;

}

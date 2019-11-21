package com.dpf.movies.dto;

import com.dpf.movies.core.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActorOutDTO extends BaseDTO {
	
	private Long id;

	private String name;

}

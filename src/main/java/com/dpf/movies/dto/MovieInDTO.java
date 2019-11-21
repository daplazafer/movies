package com.dpf.movies.dto;

import com.dpf.movies.core.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieInDTO extends BaseDTO {

    private String title;

    private Long genreId;

    private Integer year;

    private List<Long> actors;

}

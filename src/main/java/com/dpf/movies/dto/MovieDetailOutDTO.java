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
public class MovieDetailOutDTO extends BaseDTO {

    private Long id;

    private String title;

    private int year;

    private GenreOutDTO genre;

    private List<ActorOutDTO> actors;

}

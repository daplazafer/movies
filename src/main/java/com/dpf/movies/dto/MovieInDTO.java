package com.dpf.movies.dto;

import com.dpf.movies.common.base.BaseDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

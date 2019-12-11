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
public class MeOutDTO extends BaseDTO {

    private String name;

    private List<String> roles;

}

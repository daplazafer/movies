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
public class MeOutDTO extends BaseDTO {

    private String name;

    private List<String> roles;

}

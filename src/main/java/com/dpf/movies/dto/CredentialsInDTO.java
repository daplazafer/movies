package com.dpf.movies.dto;

import com.dpf.movies.common.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CredentialsInDTO extends BaseDTO {

    private String name;

    private String passwordHash;

}

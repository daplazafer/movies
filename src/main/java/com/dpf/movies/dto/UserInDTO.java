package com.dpf.movies.dto;

import com.dpf.movies.common.base.BaseDTO;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInDTO extends BaseDTO {

    private String name;

    private String passwordHash;

    private List<String> roles;

    public UserInDTO(String name, String passwordHash, String... roles) {
        this(name, passwordHash, Stream.of(roles).collect(Collectors.toList()));
    }

}

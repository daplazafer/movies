package com.dpf.movies.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String passwordHash;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    public User(Long id, String name, String passwordHash, String... roles) {
        this.id = id;
        this.name = name;
        this.passwordHash = passwordHash;
        this.roles = Stream.of(roles).collect(Collectors.toList());
    }

}

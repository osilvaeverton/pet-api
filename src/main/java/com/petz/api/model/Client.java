package com.petz.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Pet> pets;

    @NotNull
    private LocalDate birthDate;

    @NotNull
    private String cpf;

    @NotNull
    private String telefone;

    @NotNull
    private String email;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime editedAt;

}


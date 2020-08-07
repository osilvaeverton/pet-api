package com.petz.api.model;

import javax.validation.constraints.NotNull;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

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


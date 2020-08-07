package com.petz.api.model.dto.client;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ClientUpdateDTO {

    @Id
    @NotNull
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String cpf;

    private String telefone;

    private String email;

    @JsonIgnore
    private final LocalDateTime editedAt = LocalDateTime.now();

}

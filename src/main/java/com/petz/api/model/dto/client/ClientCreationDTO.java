package com.petz.api.model.dto.client;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientCreationDTO {

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

    @JsonIgnore
    private final LocalDateTime createdAt = LocalDateTime.now();

    @JsonIgnore
    private final LocalDateTime editedAt = LocalDateTime.now();

}

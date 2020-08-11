package com.petz.api.resources.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientUpdateDTO {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String cpf;

    private String telefone;

    private String email;

    @JsonIgnore
    private final LocalDateTime editedAt = LocalDateTime.now();

}

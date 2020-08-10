package com.petz.api.controller.dto.client;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String cpf;
    private String telefone;
    private String email;
}

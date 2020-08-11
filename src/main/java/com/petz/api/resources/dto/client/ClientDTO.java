package com.petz.api.resources.dto.client;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
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

package com.petz.api.resources.dto.pet;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PetDTO {

    private Long id;
    private String nome;
    private Long clientId;

}

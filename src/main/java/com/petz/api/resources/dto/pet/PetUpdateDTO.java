package com.petz.api.resources.dto.pet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PetUpdateDTO {

    private String nome;
    private Long clientId;

    @JsonIgnore
    private final LocalDateTime editedAt = LocalDateTime.now();

}

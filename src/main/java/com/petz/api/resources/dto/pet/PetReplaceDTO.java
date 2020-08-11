package com.petz.api.resources.dto.pet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PetReplaceDTO {

    @NotNull
    private String nome;

    @NotNull
    private Long client_id;

    @JsonIgnore
    private final LocalDateTime editedAt = LocalDateTime.now();

}

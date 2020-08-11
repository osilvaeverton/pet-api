package com.petz.api.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String nome;

    @ManyToOne
    private Client client;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime editedAt;

}

package com.petz.api.controller;

import com.petz.api.model.dto.client.ClientCreationDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.Set;

@RestController("/clients")
public class ClientController {

    @GetMapping
    public CollectionModel<ClientCreationDTO> findAll(){
        ClientCreationDTO clientCreationDTO = ClientCreationDTO.builder()
                .firstName("Everton")
                .lastName("Silva")
                .email("everton_ere93@hotmail.com")
                .telefone("11998241145")
                .birthDate(LocalDate.of(1993, Month.AUGUST, 25))
                .cpf("40666833800")
                .build();

        Set<ClientCreationDTO> clientCreationDTOS = Collections.singleton(clientCreationDTO);
        CollectionModel<ClientCreationDTO> clientDTOEntityModel = CollectionModel.of(clientCreationDTOS);

        return clientDTOEntityModel;
    }

    @GetMapping("/{clienteId}")
    public EntityModel<ClientCreationDTO> findOne(@PathVariable("clienteId") Long clienteId){
        ClientCreationDTO clientCreationDTO = ClientCreationDTO.builder()
                .firstName("Everton")
                .lastName("Silva")
                .email("everton_ere93@hotmail.com")
                .telefone("11998241145")
                .birthDate(LocalDate.of(1993, Month.AUGUST, 25))
                .cpf("40666833800")
                .build();

        EntityModel<ClientCreationDTO> clientDTOEntityModel = EntityModel.of(clientCreationDTO);

        return clientDTOEntityModel;
    }

    @PostMapping
    public EntityModel<ClientCreationDTO> create(@RequestBody ClientCreationDTO clientCreationDTO){
        EntityModel<ClientCreationDTO> clientDTOEntityModel = EntityModel.of(clientCreationDTO);

        return clientDTOEntityModel;
    }

    @PutMapping("/{clienteId}")
    public EntityModel<ClientCreationDTO> replace(@RequestBody ClientCreationDTO clientCreationDTO, @PathVariable("clienteId") Long clienteId){
        EntityModel<ClientCreationDTO> clientDTOEntityModel = EntityModel.of(clientCreationDTO);

        return clientDTOEntityModel;
    }

    @PatchMapping("/{clienteId}")
    public EntityModel<ClientCreationDTO> update(@RequestBody ClientCreationDTO clientCreationDTO, @PathVariable("clienteId") Long clienteId){
        EntityModel<ClientCreationDTO> clientDTOEntityModel = EntityModel.of(clientCreationDTO);

        return clientDTOEntityModel;
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity delete(@PathVariable("clienteId") Long clienteId){
        return null;
    }

}

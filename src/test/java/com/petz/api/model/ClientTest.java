package com.petz.api.model;

import com.petz.api.model.dto.client.ClientUpdateDTO;
import com.petz.api.model.dto.client.ClientCreationDTO;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.time.Month;

public class ClientTest {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void checkClientMapping(){

        ClientCreationDTO clientCreationDTO = ClientCreationDTO.builder()
                .firstName("Everton")
                .lastName("Silva")
                .email("everton_ere93@hotmail.com")
                .telefone("11998241145")
                .birthDate(LocalDate.of(1993, Month.AUGUST, 25))
                .cpf("40666833800")
                .build();

        Client client = modelMapper.map(clientCreationDTO, Client.class);
        assertEquals(clientCreationDTO.getFirstName(), client.getFirstName());
        assertEquals(clientCreationDTO.getLastName(), client.getLastName());
        assertEquals(clientCreationDTO.getCpf(), client.getCpf());
        assertEquals(clientCreationDTO.getTelefone(), client.getTelefone());
        assertEquals(clientCreationDTO.getEmail(), client.getEmail());
        assertEquals(clientCreationDTO.getCreatedAt(), client.getCreatedAt());
        assertEquals(clientCreationDTO.getEditedAt(), client.getEditedAt());

        ClientUpdateDTO clientUpdateDTO = ClientUpdateDTO.builder()
                .email("everton@gmail.com")
                .telefone("1199999999")
                .build();

        modelMapper.map(clientUpdateDTO, client);
        assertEquals(clientUpdateDTO.getEmail(), client.getEmail());
        assertEquals(clientUpdateDTO.getTelefone(), client.getTelefone());
        assertEquals(clientUpdateDTO.getEditedAt(), client.getEditedAt());
    }

}

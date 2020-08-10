package com.petz.api.controller;

import com.petz.api.controller.dto.client.ClientCreationDTO;
import com.petz.api.controller.dto.client.ClientDTO;
import com.petz.api.controller.dto.client.ClientUpdateDTO;
import com.petz.api.exception.client.ClientNotFoundException;
import com.petz.api.model.Client;
import com.petz.api.service.ClientService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public CollectionModel<ClientDTO> list(){

        List<Client> clients = clientService.findAll();
        List<ClientDTO> clientDTOList = clients
                .stream()
                .map(client -> modelMapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());

        return CollectionModel.of(clientDTOList);
    }

    @GetMapping("/{clientId}")
    public EntityModel<ClientDTO> findOne(@PathVariable("clientId") Long clientId){

        Client client = clientService.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);

        return EntityModel.of(clientDTO);
    }

    @PostMapping
    public EntityModel<ClientDTO> create(@RequestBody ClientCreationDTO clientCreationDTO){

        Client client = modelMapper.map(clientCreationDTO, Client.class);
        Client savedClient = clientService.save(client);
        ClientDTO clientDTO = modelMapper.map(savedClient, ClientDTO.class);

        return EntityModel.of(clientDTO);
    }

    @PutMapping("/{clientId}")
    public EntityModel<ClientDTO> replace(@RequestBody ClientUpdateDTO clientUpdateDTO, @PathVariable("clientId") Long clientId){

        ClientDTO clientDTO;
        Client client = modelMapper.map(clientUpdateDTO, Client.class);
        if(clientService.findById(clientId).isPresent()){
            client.setId(clientId);
            Client savedClient = clientService.save(client);
            clientDTO = modelMapper.map(savedClient, ClientDTO.class);
        } else {
            Client savedClient = clientService.save(client);
            clientDTO = modelMapper.map(savedClient, ClientDTO.class);
        }

        return EntityModel.of(clientDTO);
    }

    @PatchMapping("/{clientId}")
    public EntityModel<ClientDTO> update(@RequestBody ClientUpdateDTO clientUpdateDTO, @PathVariable("clientId") Long clientId){

        Client client = clientService.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(clientUpdateDTO, client);
        Client savedClient = clientService.save(client);
        ClientDTO clientDTO = modelMapper.map(savedClient, ClientDTO.class);

        return EntityModel.of(clientDTO);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity remove(@PathVariable("clientId") Long clientId){
        Client client = clientService.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        clientService.remove(clientId);

        return new ResponseEntity<ClientDTO>(HttpStatus.OK);
    }

}

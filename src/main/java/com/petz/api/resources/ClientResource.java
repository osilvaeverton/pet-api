package com.petz.api.resources;

import com.petz.api.repository.ClientRepository;
import com.petz.api.resources.dto.client.ClientCreationDTO;
import com.petz.api.resources.dto.client.ClientDTO;
import com.petz.api.resources.dto.client.ClientReplaceDTO;
import com.petz.api.resources.dto.client.ClientUpdateDTO;
import com.petz.api.exception.client.ClientNotFoundException;
import com.petz.api.model.Client;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public CollectionModel<ClientDTO> list(){

        List<Client> clientEntities = clientRepository.findAll();
        List<ClientDTO> clients = clientEntities
                .stream()
                .map(client -> modelMapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());

        return CollectionModel.of(clients);
    }

    @GetMapping("/{clientId}")
    public EntityModel<ClientDTO> findOne(@PathVariable("clientId") Long clientId){

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        ClientDTO clientDTO = modelMapper.map(client, ClientDTO.class);

        return EntityModel.of(clientDTO);
    }

    @PostMapping
    public EntityModel<ClientDTO> create(@RequestBody ClientCreationDTO clientCreationDTO){

        Client client = modelMapper.map(clientCreationDTO, Client.class);
        Client savedClient = clientRepository.save(client);
        ClientDTO clientDTO = modelMapper.map(savedClient, ClientDTO.class);

        return EntityModel.of(clientDTO);
    }

    @PutMapping("/{clientId}")
    public EntityModel<ClientDTO> replace(@RequestBody ClientReplaceDTO clientReplaceDTO, @PathVariable("clientId") Long clientId){

        ClientDTO clientDTO;
        Client client = modelMapper.map(clientReplaceDTO, Client.class);
        Optional<Client> persistedClient = clientRepository.findById(clientId);
        if(persistedClient.isPresent()){
            client.setId(clientId);
            client.setCreatedAt(persistedClient.get().getCreatedAt());
            Client savedClient = clientRepository.save(client);
            clientDTO = modelMapper.map(savedClient, ClientDTO.class);
        } else {
            Client savedClient = clientRepository.save(client);
            clientDTO = modelMapper.map(savedClient, ClientDTO.class);
        }

        return EntityModel.of(clientDTO);
    }

    @PatchMapping("/{clientId}")
    public EntityModel<ClientDTO> update(@RequestBody ClientUpdateDTO clientUpdateDTO, @PathVariable("clientId") Long clientId){

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(clientUpdateDTO, client);
        Client savedClient = clientRepository.save(client);
        ClientDTO clientDTO = modelMapper.map(savedClient, ClientDTO.class);

        return EntityModel.of(clientDTO);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity remove(@PathVariable("clientId") Long clientId){
        clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));

        clientRepository.deleteById(clientId);

        return new ResponseEntity<ClientDTO>(HttpStatus.OK);
    }

}

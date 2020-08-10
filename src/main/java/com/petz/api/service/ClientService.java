package com.petz.api.service;

import com.petz.api.model.Client;
import com.petz.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long clientId){
        return clientRepository.findById(clientId);
    }

    public Client save(Client client){
        return clientRepository.save(client);
    }

    public void remove(Long clientId){
        clientRepository.deleteById(clientId);
    }

}

package com.petz.api.service;

import com.petz.api.model.Client;
import com.petz.api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public Client findById(Long clientId){
        return clientRepository.findById(clientId).get();
    }

    public Client save(Client client){
        return clientRepository.save(client);
    }

    public void remove(Long clientId){
        clientRepository.deleteById(clientId);
    }


}

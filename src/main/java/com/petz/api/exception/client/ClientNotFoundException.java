package com.petz.api.exception.client;

public class ClientNotFoundException extends RuntimeException{

    public ClientNotFoundException(Long clientId){
        super("Could not find client " + clientId);
    }
}

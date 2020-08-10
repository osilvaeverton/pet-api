package com.petz.api.exception.pet;

public class PetNotFoundException extends RuntimeException{

    public PetNotFoundException(Long petId){
        super("Could not find pet " + petId);
    }
}

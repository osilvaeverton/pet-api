package com.petz.api.service;

import com.petz.api.model.Pet;
import com.petz.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public List<Pet> findAll(){
        return petRepository.findAll();
    }

    public Optional<Pet> findById(Long petId){
        return petRepository.findById(petId);
    }

    public Pet save(Pet pet){
        return petRepository.save(pet);
    }

    public void remove(Long petId){
        petRepository.deleteById(petId);
    }

}

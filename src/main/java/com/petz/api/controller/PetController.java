package com.petz.api.controller;

import com.petz.api.controller.dto.pet.PetCreationDTO;
import com.petz.api.controller.dto.pet.PetDTO;
import com.petz.api.controller.dto.pet.PetUpdateDTO;
import com.petz.api.exception.pet.PetNotFoundException;
import com.petz.api.model.Pet;
import com.petz.api.service.PetService;
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

@RestController("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public CollectionModel<PetDTO> list(){

        List<Pet> pets = petService.findAll();
        List<PetDTO> petDTOList = pets
                .stream()
                .map(pet -> modelMapper.map(pet, PetDTO.class))
                .collect(Collectors.toList());

        return CollectionModel.of(petDTOList);
    }

    @GetMapping("/{petId}")
    public EntityModel<PetDTO> findOne(@PathVariable("petId") Long petId){

        Pet pet = petService.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));

        PetDTO petDTO = modelMapper.map(pet, PetDTO.class);

        return EntityModel.of(petDTO);
    }

    @PostMapping
    public EntityModel<PetDTO> create(@RequestBody PetCreationDTO petCreationDTO){

        Pet pet = modelMapper.map(petCreationDTO, Pet.class);
        Pet savedPet = petService.save(pet);
        PetDTO petDTO = modelMapper.map(savedPet, PetDTO.class);

        return EntityModel.of(petDTO);
    }

    @PutMapping("/{petId}")
    public EntityModel<PetDTO> replace(@RequestBody PetUpdateDTO petUpdateDTO, @PathVariable("petId") Long petId){

        PetDTO petDTO;
        Pet pet = modelMapper.map(petUpdateDTO, Pet.class);
        if(petService.findById(petId).isPresent()){
            pet.setId(petId);
            Pet savedPet = petService.save(pet);
            petDTO = modelMapper.map(savedPet, PetDTO.class);
        } else {
            Pet savedPet = petService.save(pet);
            petDTO = modelMapper.map(savedPet, PetDTO.class);
        }

        return EntityModel.of(petDTO);
    }

    @PatchMapping("/{petId}")
    public EntityModel<PetDTO> update(@RequestBody PetUpdateDTO petUpdateDTO, @PathVariable("petId") Long petId){

        Pet pet = petService.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));

        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(petUpdateDTO, pet);
        Pet savedPet = petService.save(pet);
        PetDTO petDTO = modelMapper.map(savedPet, PetDTO.class);

        return EntityModel.of(petDTO);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity remove(@PathVariable("petId") Long petId){
        Pet pet = petService.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));

        petService.remove(petId);

        return new ResponseEntity<PetDTO>(HttpStatus.OK);
    }

}

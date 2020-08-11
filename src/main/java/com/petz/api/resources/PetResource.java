package com.petz.api.resources;

import com.petz.api.exception.client.ClientNotFoundException;
import com.petz.api.model.Client;
import com.petz.api.repository.ClientRepository;
import com.petz.api.repository.PetRepository;
import com.petz.api.resources.dto.pet.PetCreationDTO;
import com.petz.api.resources.dto.pet.PetDTO;
import com.petz.api.resources.dto.pet.PetUpdateDTO;
import com.petz.api.exception.pet.PetNotFoundException;
import com.petz.api.model.Pet;
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
@RequestMapping(value = "/pets")
public class PetResource {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public CollectionModel<PetDTO> list(){

        List<Pet> pets = petRepository.findAll();
        List<PetDTO> petDTOList = pets
                .stream()
                .map(pet -> modelMapper.map(pet, PetDTO.class))
                .collect(Collectors.toList());

        return CollectionModel.of(petDTOList);
    }

    @GetMapping("/{petId}")
    public EntityModel<PetDTO> findOne(@PathVariable("petId") Long petId){

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));

        PetDTO petDTO = modelMapper.map(pet, PetDTO.class);

        return EntityModel.of(petDTO);
    }

    @PostMapping
    public EntityModel<PetDTO> create(@RequestBody PetCreationDTO petCreationDTO){

        Pet pet = modelMapper.map(petCreationDTO, Pet.class);

        Client client = clientRepository.findById(pet.getClient().getId())
                .orElseThrow(() -> new ClientNotFoundException(pet.getClient().getId()));

        pet.setClient(client);

        Pet savedPet = petRepository.save(pet);
        PetDTO petDTO = modelMapper.map(savedPet, PetDTO.class);

        return EntityModel.of(petDTO);
    }

    @PutMapping("/{petId}")
    public EntityModel<PetDTO> replace(@RequestBody PetUpdateDTO petUpdateDTO, @PathVariable("petId") Long petId){

        PetDTO petDTO;
        Pet pet = modelMapper.map(petUpdateDTO, Pet.class);
        Optional<Pet> persistedPet = petRepository.findById(petId);
        if(persistedPet.isPresent()){
            pet.setId(petId);
            Pet savedPet = petRepository.save(pet);
            savedPet.setCreatedAt(persistedPet.get().getCreatedAt());
            petDTO = modelMapper.map(savedPet, PetDTO.class);
        } else {
            Pet savedPet = petRepository.save(pet);
            petDTO = modelMapper.map(savedPet, PetDTO.class);
        }

        return EntityModel.of(petDTO);
    }

    @PatchMapping("/{petId}")
    public EntityModel<PetDTO> update(@RequestBody PetUpdateDTO petUpdateDTO, @PathVariable("petId") Long petId){

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));

        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(petUpdateDTO, pet);
        Pet savedPet = petRepository.save(pet);
        PetDTO petDTO = modelMapper.map(savedPet, PetDTO.class);

        return EntityModel.of(petDTO);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity remove(@PathVariable("petId") Long petId){
        petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(petId));

        petRepository.deleteById(petId);

        return new ResponseEntity<PetDTO>(HttpStatus.OK);
    }

}

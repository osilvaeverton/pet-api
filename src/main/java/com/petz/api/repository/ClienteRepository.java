package com.petz.api.repository;

import com.petz.api.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClienteRepository extends CrudRepository<Client, Long> {

    List<Client> findAll();
}

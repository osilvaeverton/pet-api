package com.petz.api;

import com.petz.api.controller.dto.client.ClientDTO;
import com.petz.api.model.Client;
import com.petz.api.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.support.WebStack;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL, stacks = WebStack.WEBMVC )
@SpringBootApplication
public class PetzApiApplication {

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ModelMapper modelMapper;

	public static void main(String[] args) {
		SpringApplication.run(PetzApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}

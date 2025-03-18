package com.devsuperior.dscrud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscrud.dto.ClientDto;
import com.devsuperior.dscrud.entities.Client;
import com.devsuperior.dscrud.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDto findById(Long id) {
		Optional<Client> client = repository.findById(id);
		return new ProductDto(client);
	}

}

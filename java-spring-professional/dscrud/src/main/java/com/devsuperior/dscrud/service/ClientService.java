package com.devsuperior.dscrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscrud.dto.ClientDto;
import com.devsuperior.dscrud.entities.Client;
import com.devsuperior.dscrud.repositories.ClientRepository;
import com.devsuperior.dscrud.service.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	@Transactional(readOnly = true)
	public ClientDto findById(Long id) {
		Client client = repository.findById(id)
				.orElseThrow(
						() -> new ResourceNotFoundException("Recurso não encontrado!"));
		return new ClientDto(client);
	}

}

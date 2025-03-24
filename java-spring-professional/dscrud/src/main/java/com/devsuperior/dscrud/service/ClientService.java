package com.devsuperior.dscrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscrud.dto.ClientDto;
import com.devsuperior.dscrud.entities.Client;
import com.devsuperior.dscrud.repositories.ClientRepository;
import com.devsuperior.dscrud.service.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

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
	
	@Transactional(readOnly = true)
	public Page<ClientDto> findAll(Pageable pageable) {
		Page<Client> result = repository.findAll(pageable);
		return result.map(client -> new ClientDto(client));
	}
	
	@Transactional
	public ClientDto insert(ClientDto dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDto(entity);		
	}
	
	@Transactional
	public ClientDto update(Long id, ClientDto dto) {
		try {
			Client entity = repository.getReferenceById(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new ClientDto(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado!");			
		}
		
	}
	
	private void copyDtoToEntity(ClientDto dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}
}

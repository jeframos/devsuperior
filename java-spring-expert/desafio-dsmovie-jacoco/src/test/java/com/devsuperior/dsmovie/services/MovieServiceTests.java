package com.devsuperior.dsmovie.services;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.entities.MovieEntity;
import com.devsuperior.dsmovie.repositories.MovieRepository;
import com.devsuperior.dsmovie.services.exceptions.DatabaseException;
import com.devsuperior.dsmovie.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dsmovie.tests.MovieFactory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTests {
	
	@InjectMocks
	private MovieService service;

	@Mock
	private MovieRepository repository;

	private MovieEntity entity;
	private MovieDTO movieDTO;
	private PageImpl<MovieEntity> page;
	Long existingId, nonExistingId, dependentId;

	@BeforeEach
	void setUp() throws Exception {
		existingId = 1L;
		nonExistingId = 2L;
		dependentId = 3L;

		entity = MovieFactory.createMovieEntity();
		movieDTO = new MovieDTO(entity);
		page = new PageImpl<>(List.of(entity));
	}
	
	@Test
	public void findAllShouldReturnPagedMovieDTO() {
		Mockito.when(repository.searchByTitle(any(), (Pageable)any())).thenReturn(page);

		Pageable pageable = PageRequest.of(0, 12);
		Page<MovieDTO> result = service.findAll(entity.getTitle(), pageable);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getSize(), 1);
		Assertions.assertEquals(result.iterator().next().getTitle(), entity.getTitle());
	}


	@Test
	public void findByIdShouldReturnMovieDTOWhenIdExists() {
		Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(entity));

		MovieDTO result = service.findById(existingId);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), existingId);
		Assertions.assertEquals(result.getTitle(), entity.getTitle());
	}

	@Test
	public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

		Assertions.assertThrows(ResourceNotFoundException.class, () ->
				service.findById(nonExistingId));
	}
	
	@Test
	public void insertShouldReturnMovieDTO() {
		Mockito.when(repository.save(any())).thenReturn(entity);

		MovieDTO result = service.insert(movieDTO);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), movieDTO.getId());
		Assertions.assertEquals(result.getTitle(), movieDTO.getTitle());
	}
	
	@Test
	public void updateShouldReturnMovieDTOWhenIdExists() {
		Mockito.when(repository.getReferenceById(existingId)).thenReturn(entity);
		Mockito.when(repository.save(any())).thenReturn(entity);

		MovieDTO result = service.update(existingId, movieDTO);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), movieDTO.getId());
		Assertions.assertEquals(result.getTitle(), movieDTO.getTitle());
	}
	
	@Test
	public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

		Assertions.assertThrows(ResourceNotFoundException.class, () ->
				service.update(nonExistingId, movieDTO));
	}
	
	@Test
	public void deleteShouldDoNothingWhenIdExists() {
		Mockito.when(repository.existsById(existingId)).thenReturn(true);

		Assertions.assertDoesNotThrow(() -> service.delete(existingId));
	}
	
	@Test
	public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
		Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);

		Assertions.assertThrows(ResourceNotFoundException.class, () ->
				service.delete(nonExistingId)
		);
	}
	
	@Test
	public void deleteShouldThrowDatabaseExceptionWhenDependentId() {
		Mockito.when(repository.existsById(dependentId)).thenReturn(true);
		Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);

		Assertions.assertThrows(DatabaseException.class, () ->
				service.delete(dependentId)
		);
	}
}

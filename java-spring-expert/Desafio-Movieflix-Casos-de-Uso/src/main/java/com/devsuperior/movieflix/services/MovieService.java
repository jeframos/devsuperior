package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    public MovieRepository repository;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        Optional<Movie> obj = repository.findById(id);
        Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new MovieDetailsDTO(entity, entity.getGenre());
    }

    @Transactional(readOnly = true)
    public Page<MovieCardDTO> findByGenre(String genreId, Pageable pageable) {

        List<Long> genreIds = Arrays.asList();
        if (!"0".equals(genreId)) {
            genreIds = Arrays.asList(genreId.split(","))
                    .stream()
                    .map(Long::parseLong)
                    .toList();
        }

        Page<MovieProjection> page = repository.findByGenre(genreIds, pageable);
        List<Long> movieIds = page.map(movieProjection -> movieProjection.getId()).toList();

        List<Movie> entities = repository.searchMoviesWithGenres(movieIds);
        entities = (List<Movie>) Utils.replace(page.getContent(), entities);

        List<MovieCardDTO> dtos = entities.stream().map(m -> new MovieCardDTO(m)).toList();

        return new PageImpl<>(dtos, page.getPageable(), page.getTotalElements());
    }
}

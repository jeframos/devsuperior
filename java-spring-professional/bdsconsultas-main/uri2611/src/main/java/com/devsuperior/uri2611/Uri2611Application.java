package com.devsuperior.uri2611;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.uri2611.dto.MovieMinDTO;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import com.devsuperior.uri2611.repositories.MovieRepository;

@SpringBootApplication
public class Uri2611Application implements CommandLineRunner {
	
	@Autowired
	private MovieRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(Uri2611Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<MovieMinProjection> list = repository.search1("Action");
		List<MovieMinDTO> result1 = list.stream()
				                        .map(mMinProjection -> new MovieMinDTO(mMinProjection))
				                        .collect(Collectors.toList());
		
		System.out.println("\n *** RESULTADO SQL RAIZ: ");
		for (MovieMinDTO movie : result1) {
			System.out.println(movie);
		}
		
		System.out.println("\n\n");
		
		List<MovieMinDTO> result2 = repository.search2("Action");
		
		System.out.println("\n *** RESULTADO JPQL: ");
		for (MovieMinDTO movie : result2) {
			System.out.println(movie);
		}
		
	}
}

package com.devsuperior.uri2737.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2737.dto.LawyerMinDTO;
import com.devsuperior.uri2737.entities.Lawyer;
import com.devsuperior.uri2737.projections.LawyerMinProjection;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
	
	@Query(nativeQuery = true, value = "(SELECT name, customers_number AS customersNumber "
			+ "  FROM lawyers "
			+ " WHERE customers_number = ("
			+ "	SELECT MAX(customers_number) "
			+ "	  from lawyers "
			+ " )) "
			+ " "
			+ "UNION ALL "
			+ " "
			+ "(SELECT name, customers_number "
			+ "  FROM lawyers "
			+ " WHERE customers_number = ("
			+ "	SELECT MIN(customers_number) "
			+ "	  from lawyers "
			+ " )) "
			+ ""
			+ "UNION ALL "
			+ " "
			+ "(SELECT 'Average', ROUND(AVG(customers_number), 0) "
			+ "   FROM lawyers)")
	List<LawyerMinProjection> search1();

}

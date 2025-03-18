package com.devsuperior.dscrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dscrud.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}

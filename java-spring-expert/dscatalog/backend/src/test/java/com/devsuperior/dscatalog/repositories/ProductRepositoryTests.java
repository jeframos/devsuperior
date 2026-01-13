package com.devsuperior.dscatalog.repositories;

import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

//Carrega somente os componentes relacionados ao Spring Data JPA.
// Cada teste é transacional e dá rollback ao final. (teste de unidade: repository)
@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private long existingId;
    private long countTotalProducts;
    private long nonExistingId;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        //Arrange: instancie os objetos necessários
        Product product = Factory.createProduct();
        product.setId(null);

        //Act: execute as ações necessárias
        product = repository.save(product);

        //Assert: declare o que deveria acontecer (resultado esperado)
            //Valida se o "product.getId()" é null
        Assertions.assertNotNull(product.getId());
            //Valida se "countTotalProducts + 1 == 26" corresponde ao valor do id incrementado na entidade "Product"
        Assertions.assertEquals(countTotalProducts + 1, product.getId());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        //Arrange: instancie os objetos necessários
        //long existingId = 1L;  // Variavel adicionada no @BeforeEach

        //Act: execute as ações necessárias
        repository.deleteById(existingId);

        //Assert: declare o que deveria acontecer (resultado esperado)
        Optional<Product> result = repository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void findByIdShoudReturnNonEmptyOptionalWhenIdExists() {
        //Arrange: instancie os objetos necessários
        //long existingId = 1L;  // Variavel adicionada no @BeforeEach

        //Act: execute as ações necessárias
        Optional<Product> result = repository.findById(existingId);

        //Assert: declare o que deveria acontecer (resultado esperado)
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdShoudReturnEmptyOptionalWhenIdDoesNotExists() {
        //Arrange: instancie os objetos necessários

        //Act: execute as ações necessárias
        Optional<Product> result = repository.findById(nonExistingId);

        //Assert: declare o que deveria acontecer (resultado esperado)
        Assertions.assertTrue(result.isEmpty());
    }
}

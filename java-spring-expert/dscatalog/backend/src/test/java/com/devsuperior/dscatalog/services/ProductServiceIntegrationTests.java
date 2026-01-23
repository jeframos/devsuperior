package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//Carrega o contexto da aplicação (teste de integração)
@SpringBootTest
public class ProductServiceIntegrationTests {

    //Devido ser um teste de Integração entre o service e repository, o repository não será mockado.
    //Então será carregado os objetos do service utilizando o "@Autowired".
    //E não será usado o método "when().thenReturn()" dentro do método "setUp()".
    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalProducts;

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;
    }

    @Test
    public void deleteShouldDeleteResourceWhenIdExists() {

        service.delete(existingId);

        //Devido o teste estar efetuando a integração real entre service e repository
        //O parametro do resultado esperado é passado o "countTotalProducts - 1 == 24"
        //E o resultado obtido é verificado o valor total presente na base de dados "repository.count() == 24"
        Assertions.assertEquals(countTotalProducts - 1, repository.count());
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });
    }
}

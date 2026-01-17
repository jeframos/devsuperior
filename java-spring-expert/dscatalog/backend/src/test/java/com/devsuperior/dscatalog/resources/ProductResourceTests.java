package com.devsuperior.dscatalog.resources;


import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.services.ProductService;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.mockito.Mockito.when;

//o "@WebMvcTest" carrega o contexto, porém somente da camada web (teste de unidade: controlador)
@WebMvcTest(ProductResource.class)
public class ProductResourceTests {

    //Para efetuar testes na camada de Controle é necessário efetuar requests dos endpoints, e para efetuar essa ação é necessário
    // carregar o "MockMvc".
    @Autowired
    private MockMvc mockMvc;

    //Este objeto serve para converter um objeto java em Json
    @Autowired
    private ObjectMapper objectMapper;

    //Como o teste não necessita integrar com o Service, então adicionamos a notação "@MockitoBean".
    //Usar quando a classe de teste carrega o contexto da aplicação e precisa mockar algum bean do sistema.
    //@WebMvcTest
    //@SpringBootTest
    @MockitoBean
    private ProductService service;

    public ProductDTO productDTO;
    //Nos testes unitarios não usamos o tipo "Page<>", nos testes precisamos usar o "PageImpl<>"
    // Devido o "PageImpl<>" permitir efetuar a instanciação nos testes.
    public PageImpl<ProductDTO> page;

    public Long existingId;
    public Long nonExistingId;

    //O "@BeforeEach" carrega o comportamento esperado na simulação de chamadas do service
    @BeforeEach
    void setUp() throws Exception {

        existingId = 1L;
        nonExistingId = 2L;

        productDTO = Factory.createProductDTO();
        page = new PageImpl<>(List.of(productDTO));

        //Aqui estou simulando que:
        // Quando eu chamar o "findAllPaged" do service com qualquer argumento (ArgumentMatchers.any())
        // eu devo retornar um objeto "page" (thenReturn(page)) do tipo "PageImpl<ProductDTO>"
        when(service.findAllPaged(any())).thenReturn(page);

        when(service.findById(existingId)).thenReturn(productDTO);
        when(service.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

        //Devido a utilização do argumento "any()" do ArgumentMetchers, e necessário adicionar o método "eq()",
        //caso contrario os testes vão falhar!
        when(service.update(eq(existingId), any())).thenReturn(productDTO);
        when(service.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);
    }

    //O nome do teste inicia exatamente com o nome do método dentro da Controller "findAll"
    @Test
    public void findAllShouldReturnPage() throws Exception {

        //O método "perform()" efetua uma requisição
        //O método "get()" indica qual endpoint será chamado, no caso será "GET - /products"
        //O método "andExpect()" representa uma das Assertions feitas no teste
        mockMvc.perform(get("/products")).andExpect(status().isOk());

    }

    //Esse é o mesmo teste gerado acima, a diferença é a forma de configurar a chamada, e as Assertions.
    // Neste caso precisamos configurar uma variavel do tipo "ResultActions" para quebrar os testes como foi apresentado abaixo.
    @Test
    public void findAllShouldReturnPageV2() throws Exception {

        //Foi incluído o método "accept()" para informar que essa requisição vai aceitar como resposta o tipo JSON.
        ResultActions result =
                mockMvc.perform(get("/products")
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
    }

    @Test
    public void findByIdShouldReturnProductWhenIdExists() throws Exception {

        ResultActions result =
                mockMvc.perform(get("/products/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.description").exists());

    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {

        ResultActions result =
                mockMvc.perform(get("/products/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }

    @Test
    public void updateShouldReturnProductWhenIdExists() throws Exception {

        //Aqui é feito a conversão do OBJ Java para arquivo JSON com essa conversão para string
        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result =
                mockMvc.perform(put("/products/{id}", existingId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.description").exists());
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExists() throws Exception {

        //Aqui é feito a conversão do OBJ Java para arquivo JSON com essa conversão para string
        String jsonBody = objectMapper.writeValueAsString(productDTO);

        ResultActions result =
                mockMvc.perform(put("/products/{id}", nonExistingId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }
}

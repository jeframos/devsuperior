package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import com.devsuperior.dscatalog.tests.Factory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;

//Não carrega o contexto, mas permite usar os recursos do Spring com JUnit (teste de unidade: service/component)
@ExtendWith(SpringExtension.class)
public class ProductServiceTests {

    //Nos testes deve ser usada a notação '@InjectMocks' no lugar do '@Autowired'
    //devido o '@Autowired' injetar as dependencias da classe, logo não seria mais teste de unidade.
    @InjectMocks
    private ProductService service;

    //A notação '@Mock', é utilizado para simular o comportamento do objeto, justamente para não
    // criar dependencia com a classe repository.
    //
    //Usar '@Mock' quando a classe de teste não carrega o contexto da aplicação. É mais rápido e enxuto.
    //E é usado com a notação '@ExtendWith'.
    //
    //Também existe a notação '@MockBean', e deve ser usado quando a classe de teste carrega o contexto da aplicação e
    // precisa mockar algum bean do sistema.
    //E é usado com as notações '@WebMvcTest', ou '@SpringBootTest'
    @Mock
    private ProductRepository repository;

    @Mock
    private CategoryRepository categoryRepository;

    private long existingId;
    private long nonExistingId;
    private long dependentId;
    private Product product;
    private Category category;
    ProductDTO productDTO;
    private PageImpl<Product> page;

    @BeforeEach
    void setup() throws Exception {
        existingId = 1L;
        nonExistingId = 2L;
        dependentId = 3L;
        product = Factory.createProduct();
        category = Factory.createCategory();
        productDTO = Factory.createProductDTO();
        page = new PageImpl<>(List.of(product));

        //Simulando o comportamento do BD ao efetuar pesquisa com dados páginados
        //Essa simulação é usada no teste 'findAllPagedShouldReturnPage'
        Mockito.when(repository.findAll((Pageable) ArgumentMatchers.any())).thenReturn(page);

        //Simulando o comportamento do BD ao salvar dados
        Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(product);

        //Simulando o comportamento do BD ao efetuar pesquisa com ID existente
        Mockito.when(repository.findById(existingId)).thenReturn(Optional.of(product));

        //Simulando o comportamento do BD ao efetuar pesquisa com ID Inexistente
        Mockito.when(repository.findById(nonExistingId)).thenReturn(Optional.empty());

        Mockito.when(repository.getReferenceById(existingId)).thenReturn(product);

        Mockito.when(repository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

        Mockito.when(categoryRepository.getReferenceById(existingId)).thenReturn(category);

        Mockito.when(categoryRepository.getReferenceById(nonExistingId)).thenThrow(EntityNotFoundException.class);

        //Simulando o comportamento do BD para o teste 'deleteShouldDoNothingWhenIdExists'
        Mockito.doNothing().when(repository).deleteById(existingId);

        //Simulando a exceção quando a aplicação tentar pesquisar um id inexistente
        Mockito.doThrow(EmptyResultDataAccessException.class).when(repository).deleteById(nonExistingId);

        //Simulando a exceção quando a aplicação apresenta erro de integridade com os dados do BD
        Mockito.doThrow(DataIntegrityViolationException.class).when(repository).deleteById(dependentId);

        Mockito.when(repository.existsById(existingId)).thenReturn(true);
        Mockito.when(repository.existsById(nonExistingId)).thenReturn(false);
        Mockito.when(repository.existsById(dependentId)).thenReturn(true);
    }

    @Test
    public void updateShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.update(nonExistingId, productDTO);
        });
    }

    @Test
    public void updateShouldReturnProductDTOWhenExists() {

        //Variavel foi configurada globalmente
        //ProductDTO productDTO = Factory.createProductDTO();

        ProductDTO result = service.update(existingId, productDTO);

        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldReturnProductDTOWhenExists() {

        ProductDTO result = service.findById(existingId);

        Assertions.assertNotNull(result);
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(nonExistingId);
        });
    }


    @Test
    public void findAllPagedShouldReturnPage() {
        //O método 'PageRequest.of()' está sendo usado para simular a intanciação
        // do 'pageable' com (Num. da página e qtd. de obj presente na página)
        Pageable pageable = PageRequest.of(0, 10);

        Page<ProductDTO> result = service.findAllPaged(pageable);

        Assertions.assertNotNull(result);
        Mockito.verify(repository).findAll(pageable);
    }

    @Test
    public void deleteShouldThrowDatabaseExceptionWhenDependetId() {

        Assertions.assertThrows(DatabaseException.class, () -> {
            service.delete(dependentId);
        });
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            service.delete(nonExistingId);
        });
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists() {

        //Essa validação é para indicar não não vai existir exceção nenhuma quando testar a chamado do método
        // que retorna 'void'.
        Assertions.assertDoesNotThrow(() -> {
            service.delete(existingId);
        });

        //A classe 'Mockito' possui o assertion 'verify', ele irá validar se alguma chamada foi efetuada para o 'repository'.
        //O método validado na repositóru será o 'deleteById'
        Mockito.verify(repository, Mockito.times(1)).deleteById(existingId);
    }
}

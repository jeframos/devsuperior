package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.ProductDTO;
import com.devsuperior.dscatalog.entities.Product;
import com.devsuperior.dscatalog.repositories.ProductRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

//    @Transactional(readOnly = true)
//    public List<ProductDTO> findAll(){
//        List<Product> list = repository.findAll();
//        return list.stream()
//                   .map(listProduct -> new ProductDTO(listProduct))
//                   .collect(Collectors.toList());
//    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> list = repository.findAll(pageable);
        return list.map(listProduct -> new ProductDTO(listProduct));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Optional<Product> obj = repository.findById(id);

        /*
           O método "obj.get()" obtém o os dados presentes dentro do Optional<Product>,
           e caso os dados sejam nulos, ele retorna uma exceção (erro 500).
        */
        //Product entity = obj.get();
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));  // Essa msg de exceção será apresentada no log do terminal
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();

        //entity.setName(dto.getName());

        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        /*
         O método "repository.getReferenceById(id)" possui o mecanismo para de instanciar um OBJ provisório com os dados,
         e nesse momento não é efetuada a consulta no BD.
         A consulta ao BD só acontece quando for executado o método "repository.save(entity)".
        */
        try {
            Product entity = repository.getReferenceById(id);

            // entity.setName(dto.getName());

            entity = repository.save(entity);
            return new ProductDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}

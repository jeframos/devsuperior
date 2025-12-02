package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
        List<Category> list = repository.findAll();
        return list.stream()
                   .map(listCategory -> new CategoryDTO(listCategory))
                   .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id){
        Optional<Category> obj = repository.findById(id);

        /*
           O método "obj.get()" obtém o os dados presentes dentro do Optional<Category>,
           e caso os dados sejam nulos, ele retorna uma exceção (erro 500).
        */
        //Category entity = obj.get();
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));  // Essa msg de exceção será apresentada no log do terminal
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        /*
         O método "repository.getReferenceById(id)" possui o mecanismo para de instanciar um OBJ provisório com os dados,
         e nesse momento não é efetuada a consulta no BD.
         A consulta ao BD só acontece quando for executado o método "repository.save(entity)".
        */
        try {
            Category entity = repository.getReferenceById(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }
}

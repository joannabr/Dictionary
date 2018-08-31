package pl.joannabrania.Dictionary.models.services;

import org.springframework.stereotype.Service;
import pl.joannabrania.Dictionary.models.CategoryEntity;
import pl.joannabrania.Dictionary.models.repositories.CategoryRepository;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Iterable<CategoryEntity> getNameAllgroup(){
        return categoryRepository.findAll();
    }
}

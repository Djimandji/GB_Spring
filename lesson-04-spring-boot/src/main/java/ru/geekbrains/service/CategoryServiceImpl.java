package ru.geekbrains.service;


import org.springframework.stereotype.Service;
import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category save(Category category) {

    Category category1 = new Category(
            category.getId(),
            category.getName());
    Category saved = categoryRepository.save(category1);
        return saved;
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}

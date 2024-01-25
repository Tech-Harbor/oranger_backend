package com.example.backend.Category;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryFactory categoryFactory;

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryFactory::makeCategory)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryEntity getById(Long id) {
        return categoryRepository.getReferenceById(id);
    }

    @Override
    public CategoryDTO getOneById(Long id) {
        CategoryEntity category = categoryRepository.getReferenceById(id);
        return categoryFactory.makeCategory(category);
    }

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        CategoryEntity newCategory = CategoryEntity.builder()
                .title(categoryDTO.title())
                .information(categoryDTO.information())
                .build();

        categoryRepository.save(newCategory);
        return categoryFactory.makeCategory(newCategory);
    }

    @Override
    public CategoryDTO update(Long categoryId, CategoryDTO categoryDTO) {
        CategoryEntity category = categoryRepository.getReferenceById(categoryId);
        category.setTitle(categoryDTO.title());
        category.setInformation(categoryDTO.information());
        categoryRepository.save(category);
        return categoryFactory.makeCategory(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
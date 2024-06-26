package com.example.backend.web.Category;

import com.example.backend.web.Category.store.CategoryEntity;
import com.example.backend.web.Category.store.dto.CategoryCreateDTO;
import com.example.backend.web.Category.store.dto.CategoryDTO;
import com.example.backend.web.Category.store.factory.CategoryCreateFactory;
import com.example.backend.web.Category.store.factory.CategoryFactory;
import com.example.backend.web.File.ImageService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryCreateFactory categoryCreateFactory;
    private final CategoryRepository categoryRepository;
    private final CategoryFactory categoryFactory;
    private final ImageService imageService;

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryFactory)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryEntity getCategoryName(final String name) {
        return categoryRepository.getByName(name);
    }

    @Override
    public CategoryDTO getCategoryDTOName(final String name) {
        return categoryFactory.apply(categoryRepository.getByName(name));
    }

    @Override
    @Transactional
    public CategoryCreateDTO create(final CategoryCreateDTO categoryDTO, final MultipartFile image) {
        final var newImage = imageService.uploadImageEntity(image);

        final var newCategory = CategoryEntity.builder()
                .name(categoryDTO.name())
                .image(newImage)
                .build();

        return categoryCreateFactory.apply(categoryRepository.save(newCategory));
    }

    @Override
    @Transactional
    public CategoryCreateDTO update(final String name,
                                    final CategoryCreateDTO categoryDTO,
                                    final MultipartFile image) {
        final var category = getCategoryName(name);
        final var uploadImage = imageService.uploadImageEntity(image);

        if (StringUtils.isNoneEmpty(categoryDTO.name())) {
            category.setName(categoryDTO.name());
        }

        if (StringUtils.isNoneEmpty(categoryDTO.image())) {
            category.setImage(uploadImage);
        }

        return categoryCreateFactory.apply(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategory(final CategoryDTO categoryDTO) {
        final var deleteName = getCategoryName(categoryDTO.name());

        categoryRepository.delete(deleteName);
    }
}
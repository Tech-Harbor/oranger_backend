package com.example.backend.web.Category;

import com.example.backend.utils.annotations.ApiResponseCreated;
import com.example.backend.utils.annotations.ApiResponseDelete;
import com.example.backend.utils.annotations.ApiResponseOK;
import com.example.backend.web.Category.store.dto.CategoryCreateDTO;
import com.example.backend.web.Category.store.dto.CategoryDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "CategoryService")
@RequestMapping("/api")
public class CategoryController {

    private final CategoryServiceImpl categoryService;
    private static final String URI_CATEGORIES_NAME = "/category/update";
    private static final String URI_CATEGORY = "/category";
    private static final String URI_CATEGORIES = "/categories";
    private static final String URI_CATEGORY_DELETE = "/category/delete";

    @GetMapping(URI_CATEGORIES)
    @QueryMapping
    public List<CategoryDTO> getAllCategory() {
        return categoryService.getAll();
    }

    @QueryMapping
    public CategoryDTO getByNameCategory(@Argument final String name) {
        return categoryService.getCategoryDTOName(name);
    }

    @PostMapping(value = URI_CATEGORY, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiResponseCreated
    public CategoryCreateDTO create(@RequestPart @Validated final CategoryCreateDTO categoryDTO,
                                    @RequestPart final MultipartFile image) {
        return categoryService.create(categoryDTO, image);
    }

    @PatchMapping(value = URI_CATEGORIES_NAME, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ApiResponseOK
    public CategoryCreateDTO update(@RequestParam final String name,
                                    @RequestPart @Validated final CategoryCreateDTO categoryDTO,
                                    @RequestPart final MultipartFile image) {
        return categoryService.update(name, categoryDTO, image);
    }

    @DeleteMapping(URI_CATEGORY_DELETE)
    @ApiResponseDelete
    public void deleteCategory(@RequestBody final CategoryDTO categoryDTO) {
        categoryService.deleteCategory(categoryDTO);
    }
}
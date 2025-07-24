package com.example.BlogPlatform.controllers;


import com.example.BlogPlatform.Services.CategoryService;
import com.example.BlogPlatform.domain.dto.CategoryDto;
import com.example.BlogPlatform.domain.dto.CreateCategoryRequest;
import com.example.BlogPlatform.domain.entities.Category;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.BlogPlatform.mappers.CategoryMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;



    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories(){
        List<Category> categories = categoryService.listCategories();
            return ResponseEntity.ok(
                    categories.stream().map(categoryMapper::toDto).toList()
            );
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest){
        Category category = categoryMapper.toEntity(createCategoryRequest);
        Category savedCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(categoryMapper.toDto(savedCategory), HttpStatus.CREATED);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}

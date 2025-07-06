package com.example.BlogPlatform.controllers;


import com.example.BlogPlatform.Services.CategoryService;
import com.example.BlogPlatform.domain.dto.CategoryDto;
import com.example.BlogPlatform.domain.entities.Category;
import lombok.RequiredArgsConstructor;
import com.example.BlogPlatform.mappers.CategoryMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}

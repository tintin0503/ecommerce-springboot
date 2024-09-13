package com.dev.ecommerce.service;

import com.dev.ecommerce.entity.Category;
import com.dev.ecommerce.payload.CategoryDto;
import com.dev.ecommerce.payload.response.CategoryResponse;

public interface CategoryService {

    CategoryDto createCategory(Category category);

    CategoryResponse getCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    CategoryDto updateCategory(Category category, Long categoryId);

    String deleteCategory(Long categoryId);
}

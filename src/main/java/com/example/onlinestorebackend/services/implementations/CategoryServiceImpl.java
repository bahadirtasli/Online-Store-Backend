package com.example.onlinestorebackend.services.implementations;

import com.example.onlinestorebackend.exceptions.CategoryNotFoundException;
import com.example.onlinestorebackend.models.Category;
import com.example.onlinestorebackend.repositories.CategoryRepository;
import com.example.onlinestorebackend.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/23/2023
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findCategoryByName(String name) throws CategoryNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);

        if (optionalCategory.isEmpty()) {
            throw new CategoryNotFoundException(name);
        }
        return optionalCategory.get();
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategoryByName(String name) throws CategoryNotFoundException {
        Category category = findCategoryByName(name);
        category.setActive(false);
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public void restoreCategoryByName(String name) throws CategoryNotFoundException {
        Category category = findCategoryByName(name);
        category.setActive(true);
        categoryRepository.saveAndFlush(category);
    }
}
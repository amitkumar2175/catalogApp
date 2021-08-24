package com.demo.catalogApp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRespository;

    @Autowired
    public CategoryService(CategoryRepository productRespository) {
        this.categoryRespository = productRespository;
    }

    public List<Category> findAll() {
        return categoryRespository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRespository.findById(id);
    }

    public Category save(Category stock) {
        return categoryRespository.save(stock);
    }

    public void deleteById(Long id) {
        categoryRespository.deleteById(id);
    }

}

package com.cancodevery.ecom.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> findAll() {
        return  categoryDao.findAll();
    }

    @Override
    public Category findById(int id) {

        return categoryDao.findById(id).get();
    }

    @Override
    public Category save(Category category) {

        return categoryDao.save(category);
    }
}

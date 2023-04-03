package com.cancodevery.ecom.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path = "/api/v1/categories")
public class CategoryController
{

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<Category> getAll(){

        return categoryService.findAll();

    }
    @PostMapping("/")
    public Category addCategory(@RequestBody Category category){

        return categoryService.save(category);
    }
}

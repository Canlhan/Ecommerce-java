package com.cancodevery.ecom.category;

import java.util.List;

public interface CategoryService
{

    List<Category> findAll();
    Category findById(int id);

    Category save(Category category);
}

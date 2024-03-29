package com.cancodevery.ecom.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category,Integer>
{

    Category findCategoryByCategoryName(String categoryName);
}

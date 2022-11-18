package com.cancodevery.ecom.product;

import java.util.List;

public interface ProductService
{

    List<Product> getAll();
    Product get(int id);

    Product save(Product product);
}

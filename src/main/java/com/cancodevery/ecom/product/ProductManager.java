package com.cancodevery.ecom.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager  implements ProductService{

    private ProductDao productDao;

    @Autowired
    public ProductManager(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }

    @Override
    public Product get(int id) {
        return  productDao.findById(id).get();
    }

    @Override
    public Product save(Product product) {
        return productDao.save(product);
    }
}

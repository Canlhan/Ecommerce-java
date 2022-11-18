package com.cancodevery.ecom.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController
{
    private ProductService  productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> getAll(){

        return productService.getAll();
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product){

        return productService.save(product);
    }
}

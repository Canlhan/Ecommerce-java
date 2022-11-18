package com.cancodevery.ecom.carproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cartproducts")
public class CartProductController
{

    private CartProductService cartProductService;


    @Autowired
    public CartProductController(CartProductService cartProductService) {
        this.cartProductService = cartProductService;
    }


    @GetMapping("/")
    public List<CartProduct> getAll(){

        return cartProductService.getAll();
    }
}

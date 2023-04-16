package com.cancodevery.ecom.carproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public void addCartProduct(@RequestBody CartProductRequestDto cartProductRequestDto)
    {
        cartProductService.save(cartProductRequestDto);
    }


}

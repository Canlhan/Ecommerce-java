package com.cancodevery.ecom.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/carts")
public class CartController
{
    private CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Cart>> getAll(){

        return ResponseEntity.ok(cartService.getAll());
    }

    @GetMapping("/{customerId}")
    public Cart getByCustomerId(@PathVariable int customerId){

        return cartService.getByCustomerId(customerId);
    }

    @PostMapping("/")
    public ResponseEntity<Cart> add(@RequestBody Cart cart){
        return ResponseEntity.ok(cartService.save(cart));
    }
}

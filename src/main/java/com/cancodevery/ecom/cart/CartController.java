package com.cancodevery.ecom.cart;

import com.cancodevery.ecom.carproduct.CartProductRequestDto;
import com.cancodevery.ecom.customer.Customer;
import com.cancodevery.ecom.customer.CustomerRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/carts")
@Slf4j
public class CartController
{
    private CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CartResponseDto>> getAll(){

        return ResponseEntity.ok(cartService.getAll());
    }

    @GetMapping("/{customerId}")
    public CartResponseDto getByCustomerId(@PathVariable int customerId){

        return cartService.getByCustomerId(customerId);
    }

    @PostMapping("/add")
    public ResponseEntity<CartResponseDto> add(@RequestBody CartRequestDto customer,@RequestParam int customerId){

        log.info("{cart controllerda }",customer);

        return ResponseEntity.ok(cartService.save(customer,customerId));
    }
    @PostMapping("/addproduct/{cartId}")
    public ResponseEntity<CartResponseDto> addProduct(@RequestBody CartProductRequestDto cartProductRequestDto,int cartId){

        return ResponseEntity.ok(cartService.addProduct(cartProductRequestDto,cartId));
    }
}

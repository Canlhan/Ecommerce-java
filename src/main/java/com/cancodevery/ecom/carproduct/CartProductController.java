package com.cancodevery.ecom.carproduct;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/cartproducts")
@RequiredArgsConstructor
public class CartProductController
{

    private final CartProductService cartProductService;



    @GetMapping("/")
    public ResponseEntity<List<CartProductResponseDto>> getAllCartProducts()
    {
        return ResponseEntity.ok(cartProductService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<List<CartProductResponseDto>> addCartProduct(@RequestBody CartProductRequestDto cartProductRequestDto)
    {
       return  ResponseEntity.ok(cartProductService.save(cartProductRequestDto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CartProductResponseDto> getCartProduct(@PathVariable int id)
    {
        return ResponseEntity.ok(cartProductService.get(id));
    }


}

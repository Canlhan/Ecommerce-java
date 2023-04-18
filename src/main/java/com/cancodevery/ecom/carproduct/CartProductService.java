package com.cancodevery.ecom.carproduct;

import com.cancodevery.ecom.cart.Cart;

import java.util.List;

public interface CartProductService
{
    List<CartProductResponseDto> getAll();

    CartProductResponseDto get(int id);



    List<CartProductResponseDto> save(CartProductRequestDto cartProduct);

}

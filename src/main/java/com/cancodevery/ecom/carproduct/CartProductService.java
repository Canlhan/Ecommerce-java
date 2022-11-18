package com.cancodevery.ecom.carproduct;

import com.cancodevery.ecom.cart.Cart;

import java.util.List;

public interface CartProductService
{
    List<CartProduct> getAll();

    CartProduct get(int id);



    CartProduct save(CartProduct cartProduct);

}

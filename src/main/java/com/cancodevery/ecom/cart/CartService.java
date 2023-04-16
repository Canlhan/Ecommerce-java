package com.cancodevery.ecom.cart;

import com.cancodevery.ecom.carproduct.CartProductRequestDto;
import com.cancodevery.ecom.customer.Customer;
import com.cancodevery.ecom.customer.CustomerRequestDto;

import java.util.List;

public interface CartService
{

    List<CartResponseDto> getAll();

    CartResponseDto get(int id);
    CartResponseDto getByCustomerId(int customerId);
    CartResponseDto save(CartRequestDto cart, int customerId);

    CartResponseDto addProduct(CartProductRequestDto cartProductRequestDto, int cartId);

}

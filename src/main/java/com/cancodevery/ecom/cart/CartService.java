package com.cancodevery.ecom.cart;

import java.util.List;

public interface CartService
{

    List<Cart> getAll();

    Cart get(int id);
    Cart getByCustomerId(int customerId);
    Cart save(Cart cart);

}

package com.cancodevery.ecom.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CartManager implements CartService{

    private CartDao cartDao;

    @Autowired
    public CartManager(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public List<Cart> getAll() {
        return cartDao.findAll();
    }

    @Override
    public Cart get(int id) {
        return cartDao.findById(id).get();
    }

    @Override
    public Cart getByCustomerId(int customerId) {
        return cartDao.findByCustomerId(customerId);
    }

    @Override
    public Cart save(Cart cart) {
        return cartDao.save(cart);
    }
}

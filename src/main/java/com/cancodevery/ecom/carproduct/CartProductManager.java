package com.cancodevery.ecom.carproduct;

import com.cancodevery.ecom.cart.Cart;
import com.cancodevery.ecom.cart.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartProductManager implements  CartProductService{

    private CartProductDao cartProductDao;



    @Autowired
    public CartProductManager(CartProductDao cartProductDao) {
        this.cartProductDao = cartProductDao;

    }

    @Override
    public List<CartProduct> getAll() {
        return cartProductDao.findAll();
    }

    @Override
    public CartProduct get(int id) {
        return cartProductDao.findById(id).get();
    }



    @Override
    public CartProduct save(CartProduct cartProduct) {
        return cartProductDao.save(cartProduct);
    }
}

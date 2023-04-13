package com.cancodevery.ecom.carproduct;

import com.cancodevery.ecom.Exception.CartNotFound;
import com.cancodevery.ecom.cart.Cart;
import com.cancodevery.ecom.cart.CartDao;
import com.cancodevery.ecom.cart.CartResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartProductManager implements  CartProductService{

    private CartProductDao cartProductDao;
    private ModelMapper modelMapper;


    @Autowired
    public CartProductManager(CartProductDao cartProductDao) {
        this.cartProductDao = cartProductDao;

    }

    @Override
    public List<CartProductResponseDto> getAll() {

        List<CartProductResponseDto> cartProductResponseDtos= cartProductDao.findAll().stream().
                map(cartProduct ->modelMapper.map(cartProduct, CartProductResponseDto.class)).collect(Collectors.toList());

        return cartProductResponseDtos ;
    }

    @Override
    public CartProductResponseDto get(int id) {
        CartProduct cart = cartProductDao.findById(id).orElseThrow(()->new CartNotFound("Cart not found"));
        CartProductResponseDto cartProductResponseDto = modelMapper.map(cart, CartProductResponseDto.class);
        return cartProductResponseDto;
    }



    @Override
    public CartProductResponseDto save(CartProductRequestDto cartProduct) {

        CartProductResponseDto cartProductResponseDto = modelMapper.map(cartProduct, CartProductResponseDto.class);
        CartProduct  cartProduct1 = modelMapper.map(cartProductResponseDto, CartProduct.class);
        cartProductDao.save(cartProduct1);
        return cartProductResponseDto;
    }
}

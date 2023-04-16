package com.cancodevery.ecom.carproduct;

import com.cancodevery.ecom.Exception.CartNotFound;
import com.cancodevery.ecom.cart.Cart;
import com.cancodevery.ecom.cart.CartDao;
import com.cancodevery.ecom.cart.CartResponseDto;
import com.cancodevery.ecom.cart.CartService;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import com.cancodevery.ecom.vendorproduct.VendorProductResponseDto;
import com.cancodevery.ecom.vendorproduct.VendorProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartProductManager implements  CartProductService{

    private final CartProductDao cartProductDao;
    private final ModelMapper modelMapper;

    private final CartService   cartService;

    private final VendorProductService  vendorProductService;


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
        CartResponseDto cart = cartService.get(cartProduct.getCart().getId());
        VendorProductResponseDto vendorProductResponseDto = vendorProductService.get(cartProduct.getVendorProduct().getId());
        VendorProduct   vendorProduct = modelMapper.map(vendorProductResponseDto, VendorProduct.class);

        Cart cart1 = modelMapper.map(cart, Cart.class);

        CartProduct  cartProduct1 = modelMapper.map(cartProductResponseDto, CartProduct.class);
        cartProduct1.getCart().add(cart1);
        cartProduct1.setVendorProduct(vendorProduct);

        cartProductDao.save(cartProduct1);
        return cartProductResponseDto;
    }
}

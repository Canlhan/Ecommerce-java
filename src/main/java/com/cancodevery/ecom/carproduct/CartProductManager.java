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

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartProductManager implements  CartProductService{

    private final CartProductDao cartProductDao;
    private final ModelMapper modelMapper;

    private final CartService   cartService;

    private final CartDao cartDao;
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
    public List<CartProductResponseDto> save(CartProductRequestDto cartProductRequestDto) {


        Cart cart =cartDao.findById(cartProductRequestDto.getCartId()).orElseThrow(()->new CartNotFound("Cart not found"));

        List<CartProduct> cartProducts= saveAll(cartProductRequestDto.getVendorProducts(),cart);


        return cartProducts.stream().map(cartProduct -> modelMapper.map(cartProduct, CartProductResponseDto.class)).collect(Collectors.toList());
    }

    private  List<CartProduct> saveAll(Set<VendorProduct> vendorProducts, Cart cart)
    {

        List<CartProduct> carProducts= vendorProducts.stream().map(vendorProduct -> {
            CartProduct cartProduct = new CartProduct();
            VendorProductResponseDto vendorProductResponseDto = vendorProductService.get(vendorProduct.getId());
            VendorProduct   vendorProductOfCartProduct = modelMapper.map(vendorProductResponseDto, VendorProduct.class);
            cartProduct.getCart().add(cart);
            cartProduct.setVendorProduct(vendorProductOfCartProduct);
            cartProduct.setQuantity(vendorProduct.getQuantity());
            cart.getCartProducts().add(cartProduct);

            return cartProduct;
        }).collect(Collectors.toList());

        return cartProductDao.saveAll(carProducts);





        //        vendorProducts.forEach(vendorProduct -> {
//            CartProduct cartProduct = new CartProduct();
//            VendorProductResponseDto vendorProductResponseDto = vendorProductService.get(vendorProduct.getId());
//            VendorProduct   vendorProductOfCartProduct = modelMapper.map(vendorProductResponseDto, VendorProduct.class);
//            cartProduct.getCart().add(cart);
//            cartProduct.setVendorProduct(vendorProductOfCartProduct);
//            cartProduct.setQuantity(vendorProduct.getQuantity());
//            cart.getCartProducts().add(cartProduct);
//            cartProductDao.save(cartProduct);
//        });
    }
}

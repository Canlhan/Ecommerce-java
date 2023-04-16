package com.cancodevery.ecom.cart;

import com.cancodevery.ecom.Exception.CartNotFound;
import com.cancodevery.ecom.carproduct.CartProduct;
import com.cancodevery.ecom.carproduct.CartProductRequestDto;
import com.cancodevery.ecom.carproduct.CartProductService;

import com.cancodevery.ecom.customer.Customer;
import com.cancodevery.ecom.customer.CustomerRequestDto;
import com.cancodevery.ecom.customer.CustomerResponseDto;
import com.cancodevery.ecom.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class CartManager implements CartService{

    private final CartDao cartDao;
    private final  ModelMapper modelMapper;

    private CartProductService cartProductService;
    private final CustomerService customerService;



    @Override
    public List<CartResponseDto> getAll() {

        List<CartResponseDto> cartResponseDtos =cartDao.findAll().stream().map(cart -> modelMapper.map(cart, CartResponseDto.class))
                .collect(Collectors.toList());
        return cartResponseDtos;
    }

    @Override
    public CartResponseDto get(int id) {
        Cart cart = cartDao.findById(id).orElseThrow(()->new CartNotFound("Cart not found"));

        return modelMapper.map(cart, CartResponseDto.class);
    }

    @Override
    public CartResponseDto getByCustomerId(int customerId) {
        Cart cart = cartDao.findByCustomerId(customerId);
        if(cart==null){
            return null;
        }
        return modelMapper.map(cart, CartResponseDto.class);
    }


    @Override
    public CartResponseDto save(CartRequestDto cartRequestDto,  int customerId) {
        CartResponseDto   cartResponseDto =getByCustomerId(customerId);
        if(cartResponseDto!=null){
            return cartResponseDto;
        }
        CustomerResponseDto customer = customerService.get(customerId);
        Customer customer1 = modelMapper.map(customer, Customer.class);

        Cart cart = Cart.builder()
                .customer(customer1)
                .dateCreated(LocalDate.now())
                .build();

      /*


        */
        cartDao.save(cart);
        return  modelMapper.map(cart, CartResponseDto.class);
    }

    @Override
    public CartResponseDto addProduct(CartProductRequestDto cartProductRequestDto, int cartId) {
        Cart cart = cartDao.findById(cartId).orElseThrow(()->new CartNotFound("Cart not found"));
        CartProduct cartProduct = modelMapper.map(cartProductRequestDto, CartProduct.class);
        cart.getCartProducts().add(cartProduct);
        cartProductService.save(cartProductRequestDto);
        cartDao.save(cart);
        return modelMapper.map(cart, CartResponseDto.class);

    }
}

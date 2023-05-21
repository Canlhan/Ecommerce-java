package com.cancodevery.ecom.orderproduct;

import com.cancodevery.ecom.Exception.OrderProductNotFound;
import com.cancodevery.ecom.carproduct.CartProduct;
import com.cancodevery.ecom.carproduct.CartProductResponseDto;
import com.cancodevery.ecom.carproduct.CartProductService;
import com.cancodevery.ecom.order.Order;
import com.cancodevery.ecom.order.OrderDao;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import com.cancodevery.ecom.vendorproduct.VendorProductResponseDto;
import com.cancodevery.ecom.vendorproduct.VendorProductService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;



@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProductServiceImpl  implements OrderProductService{

    private final OrderProductDao orderProductDao;
    private final ModelMapper modelMapper;

    private final CartProductService cartProductService;


    @Override
    public List<OrderProductResponseDto> getAllByVendorId(int vendorId) {

        List<OrderProduct> orderProduct = orderProductDao.findOrderProductsByCartProduct_VendorProduct_Vendor_Id(vendorId);
        return orderProduct.stream().map(orderProdc->
                modelMapper.map(orderProdc,OrderProductResponseDto.class)

        ).collect(Collectors.toList());
    }



    @Override
    public List<OrderProductResponseDto> getAll() {

        List<OrderProduct> orderProduct = orderProductDao.findAll();
        return orderProduct.stream().map(orderProdc->
            modelMapper.map(orderProdc,OrderProductResponseDto.class)

        ).collect(Collectors.toList());
    }

    @Override
    public OrderProductResponseDto save(OrderProductRequestDto orderProductRequest)
    {
        OrderProduct orderProduct = modelMapper.map(orderProductRequest,OrderProduct.class);


        CartProductResponseDto cartProductResponseDto = cartProductService.get(orderProductRequest.getCartProductId());
        //vendorProduct.setQuantity(vendorProduct.getQuantity()-orderProductRequest.getQuantity());
       // vendorProductService.update(vendorProduct,vendorProduct.getId());

        orderProduct.setCartProduct(modelMapper.map(cartProductResponseDto, CartProduct.class));

        OrderProduct addedOrderProduct= orderProductDao.save(orderProduct);

        return modelMapper.map(addedOrderProduct,OrderProductResponseDto.class);
    }

    @Override
    public OrderProductResponseDto update(OrderProductRequestDto orderProductRequest, int id)
    {

        return null;
    }

    @Override
    public void delete(int id) {

    }
}

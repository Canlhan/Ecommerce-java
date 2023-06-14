package com.cancodevery.ecom.order;


import com.cancodevery.ecom.Exception.OrderNotFound;
import com.cancodevery.ecom.customer.Customer;
import com.cancodevery.ecom.customer.CustomerResponseDto;
import com.cancodevery.ecom.customer.CustomerService;
import com.cancodevery.ecom.orderproduct.*;
import com.cancodevery.ecom.vendor.Vendor;
import com.cancodevery.ecom.vendor.VendorResponseDto;
import com.cancodevery.ecom.vendor.VendorService;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import com.cancodevery.ecom.vendorproduct.VendorProductRequestDto;
import com.cancodevery.ecom.vendorproduct.VendorProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService
{

    private final OrderDao orderDao;
    private final ModelMapper modelMapper;

    private final VendorService vendorService;
    private  final CustomerService customerService;

    private final OrderProductService   orderProductService;

    private final VendorProductService vendorProductService;


    @Override
    public List<OrderResponse> getAll() {

        List<Order> orders = orderDao.findAll();


        return orders.stream().map(order -> modelMapper.map(order, OrderResponse.class)).collect(Collectors.toList());
    }

    @Override
    public OrderResponse get(int id) {

        Order order = orderDao.findById(id).orElseThrow(()->new OrderNotFound("Order not found"));
       Set<OrderProduct> orderProductsByVendorId= order.getOrderProducts().stream().filter(orderProduct -> orderProduct.getCartProduct().getVendorProduct().getVendor().getId()==id)
                .collect(Collectors.toSet());
       order.setOrderProducts(orderProductsByVendorId);


        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse save(OrderRequest orderRequest) {
        Order order = new Order();
        CustomerResponseDto customerResponseDto = customerService.get(orderRequest.getCustomerId());
        Customer customer = modelMapper.map(customerResponseDto, Customer.class);
        order.setCustomer(customer);
        addOrderProductsToOrder(order,orderRequest.getOrderProducts());
        order.setDateCreated(LocalDate.now());
        order.setIsConfirmed(false);
        addOrderToVendors(order,orderRequest.getVendorIds());
        orderDao.save(order);



        return modelMapper.map(order, OrderResponse.class);
    }

    @Override
    public OrderResponse update(OrderRequest orderRequest, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<OrderResponse> getOrdersByVendorId(int vendorId) {




        return null;
    }

    private void addOrderProductsToOrder(Order order, List<OrderProductRequestDto> orderProductIds){

        orderProductIds.stream().forEach(orderProduct->{
            OrderProductResponseDto     orderProductResponse= orderProductService.save(orderProduct);

            OrderProduct orderProduct1 = modelMapper.map(orderProductResponse, OrderProduct.class);
            order.getOrderProducts().add(orderProduct1);
        });

    }

    private void decreaseTheStock(OrderProductResponseDto orderProductResponseDto){
        int quantity = orderProductResponseDto.getQuantity();
      /* VendorProduct vendorProduct = orderProductResponseDto.getCartProduct().getVendorProduct();
        int stock = vendorProduct.getQuantity();
        VendorProductRequestDto vendorProductRequestDto = modelMapper.map(vendorProduct, VendorProductRequestDto.class);
        vendorProductRequestDto.setQuantity(stock-quantity);
        vendorProductService.update(vendorProductRequestDto,vendorProduct.getId());*/


    }
    private void addOrderToVendors(Order order, List<Integer> vendorIds){
        vendorIds.stream().forEach(vendorId->{
            VendorResponseDto vendorResponseDto = vendorService.get(vendorId);
            Vendor vendor = modelMapper.map(vendorResponseDto, Vendor.class);
            order.getVendors().add(vendor);
            vendor.getOrders().add(order);

        });
    }
}

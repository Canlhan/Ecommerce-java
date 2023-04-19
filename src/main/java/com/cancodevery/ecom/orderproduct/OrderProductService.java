package com.cancodevery.ecom.orderproduct;


import lombok.AllArgsConstructor;

import java.util.List;


public interface OrderProductService
{

    List<OrderProductResponseDto> getAllByVendorId(int vendorId);
    List<OrderProductResponseDto> getAll();
    OrderProductResponseDto save(OrderProductRequestDto orderProductRequest);

    OrderProductResponseDto update(OrderProductRequestDto orderProductRequest, int id);

    void delete(int id);
}

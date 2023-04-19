package com.cancodevery.ecom.orderproduct;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProductServiceImpl  implements OrderProductService{

    private final OrderProductDao orderProductDao;

    @Override
    public List<OrderProductResponseDto> getAllByVendorId(int vendorId) {
        return null;
    }

    @Override
    public List<OrderProductResponseDto> getAll() {
        return null;
    }

    @Override
    public OrderProductResponseDto save(OrderProductRequestDto orderProductRequest) {
        return null;
    }

    @Override
    public OrderProductResponseDto update(OrderProductRequestDto orderProductRequest, int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}

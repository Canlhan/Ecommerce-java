package com.cancodevery.ecom.orderproduct;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orderproduct")
@RequiredArgsConstructor
public class OrderProductController
{

    private final OrderProductService orderProductService;


    @GetMapping("/")
    public ResponseEntity<List<OrderProductResponseDto>> getAllOrderProducts()
    {
        return ResponseEntity.ok(orderProductService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<OrderProductResponseDto> saveOrderProduct(@RequestBody OrderProductRequestDto orderProductRequest)
    {
        return ResponseEntity.ok(orderProductService.save(orderProductRequest));
    }
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<OrderProductResponseDto>> getAllOrderProductsByVendorId(int vendorId)
    {
        return ResponseEntity.ok(orderProductService.getAllByVendorId(vendorId));
    }
}

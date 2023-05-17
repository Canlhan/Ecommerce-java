package com.cancodevery.ecom.order;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/order")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/")
    public ResponseEntity<List<OrderResponse>> getAll()
    {
        List<OrderResponse> orders = orderService.getAll();
        return ResponseEntity.ok(orders);

    }

    @PostMapping("/")
    public ResponseEntity<OrderResponse> save(@RequestBody OrderRequest orderRequest)
    {
        OrderResponse orderResponse = orderService.save(orderRequest);
        return ResponseEntity.ok(orderResponse);
    }

}

package com.cancodevery.ecom.order;


import com.cancodevery.ecom.orderproduct.OrderProduct;
import com.cancodevery.ecom.orderproduct.OrderProductRequestDto;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class OrderRequest
{
    private int quantity;
    private int customerId;
    private List<OrderProductRequestDto> orderProducts;

}

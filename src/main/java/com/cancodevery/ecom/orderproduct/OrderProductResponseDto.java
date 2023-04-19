package com.cancodevery.ecom.orderproduct;

import com.cancodevery.ecom.order.Order;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import lombok.Data;

@Data
public class OrderProductResponseDto
{

    int id;
    int quantity;
    Order order;

    VendorProduct vendorProduct;

}

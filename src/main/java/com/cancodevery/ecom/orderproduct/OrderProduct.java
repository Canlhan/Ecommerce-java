package com.cancodevery.ecom.orderproduct;


import com.cancodevery.ecom.order.Order;
import com.cancodevery.ecom.vendorproduct.VendorProduct;

import javax.persistence.*;

@Entity
@Table(name = "order_products")
public class OrderProduct
{

    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne(fetch = FetchType.LAZY)
    VendorProduct vendorProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    Order order;

    int quantity;

    boolean isDelivered;

}

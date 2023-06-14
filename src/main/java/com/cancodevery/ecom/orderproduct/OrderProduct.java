package com.cancodevery.ecom.orderproduct;


import com.cancodevery.ecom.carproduct.CartProduct;
import com.cancodevery.ecom.order.Order;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "order_products")
public class OrderProduct
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    CartProduct cartProduct;

    @Column(name = "quantity")
    int quantity;


    @Column(name = "isDelivered")
    boolean isDelivered;



}

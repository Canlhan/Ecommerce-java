package com.cancodevery.ecom.order;


import com.cancodevery.ecom.customer.Customer;
import com.cancodevery.ecom.orderproduct.OrderProduct;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(fetch = FetchType.LAZY)
    private  Customer customer;

    @Column(name = "date_created")
    private  LocalDate dateCreated;

    @Column(name = "isConfirmed")
    private Boolean isConfirmed;


    @OneToMany(fetch = FetchType.LAZY)
    private Set<OrderProduct> orderProducts=new HashSet<>();




}

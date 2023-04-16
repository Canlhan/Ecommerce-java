package com.cancodevery.ecom.order;


import com.cancodevery.ecom.customer.Customer;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order
{
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;


    @ManyToOne(fetch = FetchType.LAZY)
    Customer customer;

    LocalDate dateCreated;



}

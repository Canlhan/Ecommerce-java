package com.cancodevery.ecom.order;


import com.cancodevery.ecom.customer.Customer;
import com.cancodevery.ecom.orderproduct.OrderProduct;
import com.cancodevery.ecom.vendor.Vendor;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "isConfirmed")
    private Boolean isConfirmed;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "order_vendor",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "vendor_id"))
    private Set<Vendor> vendors = new HashSet<>();


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<OrderProduct> orderProducts = new HashSet<>();



}

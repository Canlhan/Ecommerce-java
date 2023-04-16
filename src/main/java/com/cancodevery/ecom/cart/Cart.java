package com.cancodevery.ecom.cart;

import com.cancodevery.ecom.carproduct.CartProduct;
import com.cancodevery.ecom.customer.Customer;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Cart
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    int id;

    @Column(name = "DateCreated")
    LocalDate dateCreated;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<CartProduct> cartProducts = new HashSet<>();
}

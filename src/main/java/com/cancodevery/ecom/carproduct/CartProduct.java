package com.cancodevery.ecom.carproduct;

import com.cancodevery.ecom.cart.Cart;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class CartProduct
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "quantity")
    int quantity;

    @ManyToMany( mappedBy = "cartProducts")
    Set<Cart> cart= new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "vendorproduct_id")
    VendorProduct vendorProduct;


}

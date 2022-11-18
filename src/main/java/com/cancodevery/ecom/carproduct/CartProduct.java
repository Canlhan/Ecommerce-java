package com.cancodevery.ecom.carproduct;

import com.cancodevery.ecom.cart.Cart;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class CartProduct
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "Quantity")
    int quantity;

    @ManyToOne()
    @JoinColumn(name = "cart_id")
    Cart cart;

    @OneToOne()
            @JoinColumn(name = "vendorproduct_id")
    VendorProduct vendorProduct;


}

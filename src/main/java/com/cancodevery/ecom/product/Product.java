package com.cancodevery.ecom.product;

import com.cancodevery.ecom.category.Category;
import com.cancodevery.ecom.customer.Customer;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@ToString
public class Product  {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
            @Column(name = "product_id")
    int  id;

    @Column(name = "product_name")
    String productName;

    @Column(name = "product_photo")
    String productPhoto;


    @Column(name = "unit_in_stock")
    int unitInStock;

    @Column(name = "unit_price")
    int unitPrice;

    @Column(name = "state")
    boolean state;


    //sonsuz döngüye girdiği için koymuştum ama jsonidentittinfo daha iyi
    //@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "category_id" ,nullable = false)
    Category category;

    @OneToOne(mappedBy = "product")
    VendorProduct vendorProduct;






}

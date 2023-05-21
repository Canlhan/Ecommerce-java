package com.cancodevery.ecom.vendor;

import com.cancodevery.ecom.order.Order;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Vendor
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_id")
    int id;
    @Column(name = "vendor_name")
    String name;

    @Column(name = "adress")
    String adress;

    @Column(name = "email")
    String email;

    @Column(name = "contact")
    String contact;

    @ManyToMany(mappedBy = "vendors",cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Order> orders=new HashSet<>();

    @OneToMany(mappedBy = "vendor",cascade = CascadeType.ALL)
    List<VendorProduct> vendorProducts=new ArrayList<>();
}

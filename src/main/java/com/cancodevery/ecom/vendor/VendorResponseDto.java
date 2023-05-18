package com.cancodevery.ecom.vendor;

import com.cancodevery.ecom.order.Order;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorResponseDto
{


    private int id;
    private String name;

    private String adress;

    private String email;

    private String contact;


    private Set<Order> orders;
     private List<VendorProduct> vendorProducts=new ArrayList<>();
}

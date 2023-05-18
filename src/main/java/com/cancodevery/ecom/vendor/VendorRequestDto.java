package com.cancodevery.ecom.vendor;

import com.cancodevery.ecom.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorRequestDto
{
    private String name;


    private String adress;


    private String email;


    private String password;


    private String contact;

    private Set<Order> orders=new HashSet<>();



}

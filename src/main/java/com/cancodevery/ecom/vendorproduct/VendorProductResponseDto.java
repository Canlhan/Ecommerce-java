package com.cancodevery.ecom.vendorproduct;

import com.cancodevery.ecom.product.Product;
import com.cancodevery.ecom.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorProductResponseDto
{

    private int id;

    private float price;


    private int quantity;


    private String description;


    private Vendor vendor;


    private Product product;
}

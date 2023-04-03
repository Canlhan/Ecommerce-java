package com.cancodevery.ecom.vendorproduct;

import com.cancodevery.ecom.product.Product;
import com.cancodevery.ecom.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VendorProductRequestDto
{
    private float price;


    private int quantity;


    private String description;


    private Vendor vendor;


    private Product product;
}

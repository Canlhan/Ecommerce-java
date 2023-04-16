package com.cancodevery.ecom.carproduct;

import com.cancodevery.ecom.cart.Cart;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import lombok.Data;

import java.util.Set;

@Data
public class CartProductRequestDto
{


    Cart cart;
    Set<VendorProduct> vendorProduct;

}

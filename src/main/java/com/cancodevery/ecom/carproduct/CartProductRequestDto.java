package com.cancodevery.ecom.carproduct;

import com.cancodevery.ecom.cart.Cart;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CartProductRequestDto
{


   private int quantity;
    private  int cartId;
    private List<VendorProduct> vendorProducts;

}

package com.cancodevery.ecom.carproduct;


import com.cancodevery.ecom.cart.Cart;
import com.cancodevery.ecom.customer.Customer;
import com.cancodevery.ecom.vendorproduct.VendorProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProductResponseDto
{

    int quantity;

    Cart cart;

    Set<VendorProduct> vendorProduct;
}

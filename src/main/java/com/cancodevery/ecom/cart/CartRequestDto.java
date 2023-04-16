package com.cancodevery.ecom.cart;


import com.cancodevery.ecom.carproduct.CartProduct;
import com.cancodevery.ecom.customer.Customer;
import com.cancodevery.ecom.customer.CustomerRequestDto;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartRequestDto
{



     Set<CartProduct> cartProducts;

}

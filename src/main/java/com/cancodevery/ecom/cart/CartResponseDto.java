package com.cancodevery.ecom.cart;


import com.cancodevery.ecom.carproduct.CartProduct;
import com.cancodevery.ecom.customer.Customer;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponseDto
{


    int id;
    Customer customer;

    Set<CartProduct> cartProducts;
    LocalDate dateCreated;
}

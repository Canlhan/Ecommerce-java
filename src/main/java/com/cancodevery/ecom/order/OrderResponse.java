package com.cancodevery.ecom.order;


import com.cancodevery.ecom.customer.Customer;
import com.cancodevery.ecom.orderproduct.OrderProduct;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Set;

@Data
public class OrderResponse
{



    private Customer customer;


    private LocalDate dateCreated;


    private Boolean isConfirmed;

    private Set<OrderProduct> orderProducts;

}

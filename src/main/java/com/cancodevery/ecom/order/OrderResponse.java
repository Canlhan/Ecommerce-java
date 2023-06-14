package com.cancodevery.ecom.order;


import com.cancodevery.ecom.customer.Customer;
import com.cancodevery.ecom.orderproduct.OrderProduct;
import com.cancodevery.ecom.orderproduct.OrderProductResponseDto;
import com.cancodevery.ecom.vendor.Vendor;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class OrderResponse
{



    private int id;
    private Customer customer;


    private LocalDate dateCreated;



    private Boolean isConfirmed;

    private Set<OrderProduct> orderProducts;

}

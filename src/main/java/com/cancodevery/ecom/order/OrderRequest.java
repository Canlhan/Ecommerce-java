package com.cancodevery.ecom.order;


import lombok.Data;

@Data
public class OrderRequest
{
    private int quantity;
    private int customerId;

}

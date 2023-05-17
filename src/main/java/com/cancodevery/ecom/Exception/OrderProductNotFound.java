package com.cancodevery.ecom.Exception;

public class OrderProductNotFound  extends RuntimeException
{
    public OrderProductNotFound(String message)
    {
        super(message);
    }
}

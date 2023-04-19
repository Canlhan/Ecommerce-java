package com.cancodevery.ecom.Exception;

public class OrderNotFound extends RuntimeException
{
    public OrderNotFound(String message)
    {
        super(message);
    }
}

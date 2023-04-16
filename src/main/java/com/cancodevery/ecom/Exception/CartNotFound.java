package com.cancodevery.ecom.Exception;

public class CartNotFound extends RuntimeException
{
    public CartNotFound(String message)
    {
        super(message);
    }
}

package com.cancodevery.ecom.Exception;

public class CartProductNotFound extends RuntimeException
{
    public CartProductNotFound(String message)
    {
        super(message);
    }
}

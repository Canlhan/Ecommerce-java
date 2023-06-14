package com.cancodevery.ecom.Exception;

public class CustomerNotFound extends RuntimeException
{
    public CustomerNotFound(String message)
    {
        super(message);
    }
}

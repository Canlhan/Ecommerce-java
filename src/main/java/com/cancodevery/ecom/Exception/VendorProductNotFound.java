package com.cancodevery.ecom.Exception;

public class VendorProductNotFound extends RuntimeException{
    public VendorProductNotFound(String message) {
        super(message);
    }
}

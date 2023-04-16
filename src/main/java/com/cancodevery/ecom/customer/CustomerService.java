package com.cancodevery.ecom.customer;


import java.util.List;

public interface CustomerService {


    List<CustomerResponseDto> getAll();

    CustomerResponseDto get(int id);


    CustomerResponseDto register(CustomerRequestDto customer);
    CustomerResponseDto getByEmail(String email);
}

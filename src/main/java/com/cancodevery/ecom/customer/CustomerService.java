package com.cancodevery.ecom.customer;


import java.util.List;

public interface CustomerService {


    List<CustomerResponseDto> getAll();

    CustomerResponseDto get(int id);


    CustomerResponseDto add(CustomerRequestDto customer);
}

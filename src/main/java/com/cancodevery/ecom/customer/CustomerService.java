package com.cancodevery.ecom.customer;


import java.util.List;

public interface CustomerService {


    List<Customer> getAll();

    Customer get(int id);


    Customer add(Customer customer);
}

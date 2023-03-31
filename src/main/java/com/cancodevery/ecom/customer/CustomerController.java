package com.cancodevery.ecom.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<CustomerResponseDto> getAll(){
        String a=null;

        return customerService.getAll();
    }

    @PostMapping("/")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customer){

        return customerService.add(customer);
    }


}

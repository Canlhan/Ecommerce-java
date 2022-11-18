package com.cancodevery.ecom.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerManager  implements CustomerService{

    private CustomerDao customerDao;

    @Autowired
    public CustomerManager(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> getAll() {
        return customerDao.findAll();
    }

    @Override
    public Customer get(int id) {
        return customerDao.findById(id).get();
    }

    @Override
    public Customer add(Customer customer) {

        return customerDao.save(customer);
    }
}

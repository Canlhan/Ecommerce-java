package com.cancodevery.ecom.customer;

import com.cancodevery.ecom.Role.Role;
import com.cancodevery.ecom.Role.RoleRepository;
import com.cancodevery.ecom.user.User;
import com.cancodevery.ecom.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerManager  implements CustomerService{

    private final UserRepository userRepository;
    private final CustomerDao customerDao;

    private final RoleRepository roleRepository;



    public CustomerManager(CustomerDao customerDao, UserRepository userRepository,RoleRepository roleRepository)
    {
        this.customerDao = customerDao;
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
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

        System.out.println("customer managerda");
        User user=new User();
        user.setEmail(customer.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));

        Role role=new Role();
        role.setRoleName("ROLE_CUSTOMER");
        role=roleRepository.save(role);
        user.getRoles().add(role);
        userRepository.save(user);

        return customerDao.save(customer);
    }
}

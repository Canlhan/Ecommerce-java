package com.cancodevery.ecom.customer;

import com.cancodevery.ecom.Role.Role;
import com.cancodevery.ecom.Role.RoleRepository;
import com.cancodevery.ecom.user.User;
import com.cancodevery.ecom.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerManager  implements CustomerService{

    private final UserRepository userRepository;
    private final CustomerDao customerDao;

    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;



    public CustomerManager(CustomerDao customerDao, UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository)
    {
        this.customerDao = customerDao;
        this.userRepository=userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository=roleRepository;
    }

    @Override
    public List<CustomerResponseDto> getAll() {
        List<CustomerResponseDto> customerResponseDtos=new ArrayList<>();
        customerDao.findAll().forEach(customer -> {
            customerResponseDtos.add(modelMapper.map(customer,CustomerResponseDto.class));
        });
        return customerResponseDtos;
    }

    @Override
    public CustomerResponseDto get(int id) {
        Customer customer=customerDao.findById(id).get();
        return modelMapper.map(customer,CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto add(CustomerRequestDto customerRequest) {

        System.out.println("customer managerda");
        User user=new User();
        user.setEmail(customerRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(customerRequest.getPassword()));

        Role role=roleRepository.findByRoleName("ROLE_CUSTOMER");

        user.getRoles().add(role);
        userRepository.save(user);

        Customer customer=modelMapper.map(customerRequest,Customer.class);
        customerDao.save(customer);
        return modelMapper.map(customer,CustomerResponseDto.class);

    }
}

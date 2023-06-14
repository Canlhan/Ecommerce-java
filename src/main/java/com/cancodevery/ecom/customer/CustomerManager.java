package com.cancodevery.ecom.customer;

import com.cancodevery.ecom.Exception.CustomerNotFound;
import com.cancodevery.ecom.auth.AuthService;
import com.cancodevery.ecom.auth.AuthenticationResponse;
import com.cancodevery.ecom.auth.RegisterRequest;
import com.cancodevery.ecom.role.RoleType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerManager implements CustomerService {



    private final CustomerDao customerDao;

    private final AuthService authService;
    private final ModelMapper modelMapper;


    @Override
    public List<CustomerResponseDto> getAll() {
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();
        customerDao.findAll().forEach(customer -> {
            customerResponseDtos.add(modelMapper.map(customer, CustomerResponseDto.class));
        });
        return customerResponseDtos;
    }

    @Override
    public CustomerResponseDto get(int id) {
        Customer customer = customerDao.findById(id).orElseThrow(() -> new CustomerNotFound("Customer not found"));
        return modelMapper.map(customer, CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto register(CustomerRequestDto customerRequest) {

        System.out.println("customer managerda");
        AuthenticationResponse authenticationResponse = authService.register(
                RegisterRequest.builder().roleType(RoleType.ROLE_CUSTOMER)
                        .email(customerRequest.getEmail()).password(customerRequest.getPassword())
                        .username(customerRequest.getFirstName()).build());



        Customer customer = modelMapper.map(customerRequest, Customer.class);
        customerDao.save(customer);
        CustomerResponseDto customerResponseDto = modelMapper.map(customer, CustomerResponseDto.class);
        customerResponseDto.setToken(authenticationResponse.getToken());
        return customerResponseDto;

    }

    @Override
    public CustomerResponseDto getByEmail(String email) {

        Customer customer = customerDao.findByEmail(email);
        return modelMapper.map(customer, CustomerResponseDto.class);

    }
}

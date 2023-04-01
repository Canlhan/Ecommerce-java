package com.cancodevery.ecom.customer;

import com.cancodevery.ecom.Role.Role;
import com.cancodevery.ecom.Role.RoleRepository;
import com.cancodevery.ecom.auth.AuthService;
import com.cancodevery.ecom.auth.AuthenticationResponse;
import com.cancodevery.ecom.auth.RegisterRequest;
import com.cancodevery.ecom.user.User;
import com.cancodevery.ecom.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerManager  implements CustomerService{


    private final CustomerDao customerDao;

    private final AuthService authService;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;





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
        AuthenticationResponse authenticationResponse =authService.register(
                RegisterRequest.builder().email(customerRequest.getEmail()).password(customerRequest.getPassword())
                .username(customerRequest.getFirstName()).build());

        log.info("customer managerda",authenticationResponse.getToken());

        Customer customer=modelMapper.map(customerRequest,Customer.class);
        customerDao.save(customer);
        CustomerResponseDto customerResponseDto=modelMapper.map(customer,CustomerResponseDto.class);
        customerResponseDto.setToken(authenticationResponse.getToken());
        return customerResponseDto;

    }
}

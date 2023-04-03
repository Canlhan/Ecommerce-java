package com.cancodevery.ecom.vendor;

import com.cancodevery.ecom.Exception.UserNotFound;
import com.cancodevery.ecom.Role.Role;
import com.cancodevery.ecom.Role.RoleRepository;
import com.cancodevery.ecom.Role.Roles;
import com.cancodevery.ecom.auth.AuthService;
import com.cancodevery.ecom.auth.AuthenticationResponse;
import com.cancodevery.ecom.auth.RegisterRequest;
import com.cancodevery.ecom.user.User;
import com.cancodevery.ecom.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VendorManager implements VendorService{
    private final VendorDao vendorDao;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final AuthService authService;


    @Override
    public List<VendorResponseDto> getAll() {
        List<VendorResponseDto> vendorResponseDtos=new ArrayList<>();
        vendorDao.findAll().stream().forEach(vendor->{
            vendorResponseDtos.add( modelMapper.map(vendor,VendorResponseDto.class));

        });
        return vendorResponseDtos;
    }

    @Override
    public VendorResponseDto get(int id) {
        Vendor vendor=vendorDao.findById(id).orElseThrow(()->new UserNotFound("Vendor not found"));

        return modelMapper.map(vendor,VendorResponseDto.class);

    }

    @Override
    public VendorResponseDto register(VendorRequestDto vendorRequestDto) {


        AuthenticationResponse authenticationResponse =authService.register(
                RegisterRequest.builder().roles(Roles.VENDOR).username(vendorRequestDto.getName()).password(vendorRequestDto.getPassword()).email(vendorRequestDto.getEmail()).build());

       Vendor vendor=modelMapper.map(vendorRequestDto,Vendor.class);
       vendorDao.save(vendor);
       log.info("vendor managerda",authenticationResponse.getToken());

       VendorResponseDto vendorResponseDto=modelMapper.map(vendor,VendorResponseDto.class);
       vendorResponseDto.setToken(authenticationResponse.getToken());

        return vendorResponseDto;
    }

    @Override
    public VendorResponseDto get(String email) {
        Vendor vendor=vendorDao.findVendorByEmail(email).orElseThrow(()->new UserNotFound("Vendor not found"));

        return modelMapper.map(vendor,VendorResponseDto.class);
    }
}

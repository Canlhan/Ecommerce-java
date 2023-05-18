package com.cancodevery.ecom.vendor;

import com.cancodevery.ecom.Exception.UserNotFound;
import com.cancodevery.ecom.role.RoleType;
import com.cancodevery.ecom.auth.AuthService;
import com.cancodevery.ecom.auth.AuthenticationResponse;
import com.cancodevery.ecom.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VendorManager implements VendorService{
    private final VendorDao vendorDao;
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
    public AuthenticationResponse register(VendorRequestDto vendorRequestDto) {


        AuthenticationResponse authenticationResponse =authService.register(
                RegisterRequest.builder().roleType(RoleType.ROLE_VENDOR)
                        .username(vendorRequestDto.getName())
                        .password(vendorRequestDto.getPassword()).email(vendorRequestDto.getEmail()).build());

       Vendor vendor=modelMapper.map(vendorRequestDto,Vendor.class);
       vendorDao.save(vendor);
       log.info("vendor managerda",authenticationResponse.getToken());



        return authenticationResponse;
    }

    @Override
    public VendorResponseDto get(String email) {
        Vendor vendor=vendorDao.findVendorByEmail(email).orElseThrow(()->new UserNotFound("Vendor not found"));

        return modelMapper.map(vendor,VendorResponseDto.class);
    }

    @Override
    public VendorResponseDto update(VendorRequestDto VendorRequest) {

        Vendor vendor=modelMapper.map(VendorRequest,Vendor.class);

        return modelMapper.map(vendorDao.save(vendor),VendorResponseDto.class);
    }
}

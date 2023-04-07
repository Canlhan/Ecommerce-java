package com.cancodevery.ecom.vendor;

import com.cancodevery.ecom.auth.AuthenticationResponse;

import java.util.List;

public interface VendorService
{
    List<VendorResponseDto> getAll();

    VendorResponseDto get(int id);

    AuthenticationResponse register(VendorRequestDto vendor);
    VendorResponseDto get(String email);
}

package com.cancodevery.ecom.vendor;

import java.util.List;

public interface VendorService
{
    List<VendorResponseDto> getAll();

    VendorResponseDto get(int id);

    VendorResponseDto register(VendorRequestDto vendor);
    VendorResponseDto get(String email);
}

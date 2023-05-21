package com.cancodevery.ecom.vendorproduct;


import com.cancodevery.ecom.dtos.VendorProductDto;

import java.util.List;

public interface VendorProductService
{

    List<VendorProductResponseDto> getAll();

    VendorProductResponseDto get(int id);

    VendorProductResponseDto save(VendorProductRequestDto vendorProductRequestDto);
    List<VendorProductResponseDto> getVendorProductsByVendorId(int vendorId);

    List<VendorProductResponseDto> getVendorProductsByCategoryId(int categoryId);

    VendorProductResponseDto update(VendorProductRequestDto vendorProduct, int id);


}

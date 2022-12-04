package com.cancodevery.ecom.vendorproduct;


import com.cancodevery.ecom.dtos.VendorProductDto;

import java.util.List;

public interface VendorProductService
{

    List<VendorProductDto> getAll();

    VendorProductDto get(int id);

    VendorProductDto save(VendorProductDto vendorProduct);
}

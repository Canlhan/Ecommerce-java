package com.cancodevery.ecom.vendorproduct;


import java.util.List;

public interface VendorProductService
{

    List<VendorProduct> getAll();

    VendorProduct get(int id);

    VendorProduct save(VendorProduct vendorProduct);
}

package com.cancodevery.ecom.vendor;

import java.util.List;

public interface VendorService
{
    List<Vendor> getAll();

    Vendor get(int id);

    Vendor save(Vendor vendor);
}

package com.cancodevery.ecom.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorManager implements VendorService{
    private VendorDao vendorDao;

    @Autowired
    public VendorManager(VendorDao vendorDao) {
        this.vendorDao = vendorDao;
    }

    @Override
    public List<Vendor> getAll() {
        return vendorDao.findAll();
    }

    @Override
    public Vendor get(int id) {
        return vendorDao.findById(id).get();
    }

    @Override
    public Vendor save(Vendor vendor) {
        return vendorDao.save(vendor);
    }
}

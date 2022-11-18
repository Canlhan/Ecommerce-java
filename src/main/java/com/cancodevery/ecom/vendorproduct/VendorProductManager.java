package com.cancodevery.ecom.vendorproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorProductManager implements VendorProductService{
    private VendorProductDao vendorProductDao;

    @Autowired
    public VendorProductManager(VendorProductDao vendorProductDao) {
        this.vendorProductDao = vendorProductDao;
    }

    @Override
    public List<VendorProduct> getAll() {
        return vendorProductDao.findAll();
    }

    @Override
    public VendorProduct get(int id) {
        return vendorProductDao.findById(id).get();
    }

    @Override
    public VendorProduct save(VendorProduct vendorProduct) {

        return vendorProductDao.save(vendorProduct);
    }
}

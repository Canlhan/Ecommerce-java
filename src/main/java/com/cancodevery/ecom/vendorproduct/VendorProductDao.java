package com.cancodevery.ecom.vendorproduct;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VendorProductDao extends JpaRepository<VendorProduct,Integer> {

    List<VendorProduct> findVendorProductsByVendorId(int vendorId);
}

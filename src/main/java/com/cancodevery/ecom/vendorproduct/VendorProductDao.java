package com.cancodevery.ecom.vendorproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorProductDao  extends JpaRepository<VendorProduct,Integer> {
}

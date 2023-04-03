package com.cancodevery.ecom.vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorDao extends JpaRepository<Vendor,Integer>
{
    Optional<Vendor> findVendorByEmail(String email);
}

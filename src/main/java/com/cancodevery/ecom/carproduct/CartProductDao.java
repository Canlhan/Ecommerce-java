package com.cancodevery.ecom.carproduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductDao extends JpaRepository<CartProduct,Integer> {
}

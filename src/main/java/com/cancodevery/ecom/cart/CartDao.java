package com.cancodevery.ecom.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<Cart,Integer> {

    Cart findByCustomerId(int customerId);
}

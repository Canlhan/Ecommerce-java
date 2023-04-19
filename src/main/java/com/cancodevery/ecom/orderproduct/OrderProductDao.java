package com.cancodevery.ecom.orderproduct;

import com.cancodevery.ecom.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductDao extends JpaRepository<OrderProduct,Integer>
{

        List<Order> findAllByVendorId(int vendorId);
}

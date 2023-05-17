package com.cancodevery.ecom.orderproduct;

import com.cancodevery.ecom.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderProductDao extends JpaRepository<OrderProduct,Integer>
{

        List<OrderProduct> findOrderProductsByVendorProduct_Vendor_Id(int vendorId);
}

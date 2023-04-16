package com.cancodevery.ecom.order;

import java.util.List;

public interface OrderService
{

    List<OrderResponse> getAll();

    OrderResponse get(int id);

    OrderResponse save(OrderRequest orderRequest, int customerId);

    OrderResponse update(OrderRequest orderRequest, int id);

    void delete(int id);


}

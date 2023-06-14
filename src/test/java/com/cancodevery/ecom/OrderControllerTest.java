package com.cancodevery.ecom.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    private OrderController orderController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        orderController = new OrderController(orderService);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testGetAllOrders() throws Exception {
        // Mock data
        OrderResponse order1 = new OrderResponse();
        OrderResponse order2 = new OrderResponse();
        List<OrderResponse> orders = Arrays.asList(order1, order2);

        // Mock orderService.getAll() method call
        when(orderService.getAll()).thenReturn(orders);

        // Perform GET request and verify the result
        mockMvc.perform(get("/api/v1/order/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(order1.getId()))
                .andExpect(jsonPath("$[1].id").value(order2.getId()));

        // Verify that orderService.getAll() method is called once
        verify(orderService, times(1)).getAll();
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void testSaveOrder() throws Exception {
        // Mock data
        OrderRequest orderRequest = new OrderRequest();
        OrderResponse savedOrder = new OrderResponse();

        // Mock orderService.save() method call
        when(orderService.save(any(OrderRequest.class))).thenReturn(savedOrder);

        // Perform POST request and verify the result
        MvcResult result = mockMvc.perform(post("/api/v1/order/")
                        .content(new ObjectMapper().writeValueAsString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = result.getResponse().getContentAsString();
        OrderResponse orderResponse = new ObjectMapper().readValue(responseContent, OrderResponse.class);
        assertEquals(savedOrder.getId(), orderResponse.getId());

        // Verify that orderService.save() method is called once with the correct argument
        verify(orderService, times(1)).save(eq(orderRequest));
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void testGetOrderById() throws Exception {
        // Mock data
        int orderId = 1;
        OrderResponse order = new OrderResponse();

        // Mock orderService.get() method call
        when(orderService.get(orderId)).thenReturn(order);

        // Perform GET request and verify the result
        mockMvc.perform(get("/api/v1/order/{id}", orderId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(order.getId()));

        // Verify that orderService.get() method is called once with the correct argument
        verify(orderService, times(1)).get(eq(orderId));
        verifyNoMoreInteractions(orderService);
    }
}

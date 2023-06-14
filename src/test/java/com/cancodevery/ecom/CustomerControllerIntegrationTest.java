package com.cancodevery.ecom.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Test verileri ve diğer hazırlıklar burada yapılabilir
    }

    @Test
    void testGetAllCustomers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(7));
    }

    @Test
    void testAddCustomer() throws Exception {
        CustomerRequestDto customerRequestDto = CustomerRequestDto.builder()
                .firstName("Can")
                .lastName("can")
                .dob(new Date())
                .email("canilhn1@gmail.com")
                .password("password123")
                .contact("5434057459")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Can"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("can"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("canilhn1@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contact").value("5434057459"));
    }

    @Test
    void testGetCustomerByEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/getemail")
                        .param("email", "canilhn1@gmail.com"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Can"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("can"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("canilhn1@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.contact").value("5434057459"));
    }
}

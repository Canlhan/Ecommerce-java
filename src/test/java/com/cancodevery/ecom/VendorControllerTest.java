import com.cancodevery.ecom.vendor.VendorController;
import com.cancodevery.ecom.vendor.VendorRequestDto;
import com.cancodevery.ecom.vendor.VendorResponseDto;
import com.cancodevery.ecom.vendor.VendorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cancodevery.ecom.auth.AuthenticationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VendorControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VendorService vendorService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        VendorController vendorController = new VendorController(vendorService);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getAll() throws Exception {
        // Arrange
        VendorResponseDto vendor1 = VendorResponseDto.builder()
                .id(1)
                .name("Vendor1")

                .email("vendor1@example.com")
                .contact("123456789")
                .build();

        VendorResponseDto vendor2 = VendorResponseDto.builder()
                .id(2)
                .name("Vendor2")

                .email("vendor2@example.com")
                .contact("987654321")
                .build();

        List<VendorResponseDto> vendors = Arrays.asList(vendor1, vendor2);

        when(vendorService.getAll()).thenReturn(vendors);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/vendors/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))

                .andExpect(jsonPath("$[0].address").value("Address1"))
                .andExpect(jsonPath("$[0].email").value("vendor1@example.com"))
                .andExpect(jsonPath("$[0].contact").value("123456789"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Vendor2"))
                .andExpect(jsonPath("$[1].address").value("Address2"))
                .andExpect(jsonPath("$[1].email").value("vendor2@example.com"))
                .andExpect(jsonPath("$[1].contact").value("987654321"));
    }

    @Test
    void add() throws Exception {
        // Arrange
        VendorRequestDto vendorRequest = VendorRequestDto.builder()
                .name("Vendor")

                .email("vendor@example.com")
                .password("password")
                .contact("123456789")
                .build();

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .token("TOKEN")
                .build();

        when(vendorService.register(vendorRequest)).thenReturn(authenticationResponse);

        // Act & Assert
        mockMvc.perform(post("/api/v1/vendors/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vendorRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("TOKEN"));
    }

    @Test
    void get() throws Exception {
        // Arrange
        VendorResponseDto vendor = VendorResponseDto.builder()
                .id(1)
                .name("Vendor")

                .email("vendor@example.com")
                .contact("123456789")
                .build();

        when(vendorService.get("vendor@example.com")).thenReturn(vendor);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/vendors/vendor@example.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Vendor"))
                .andExpect(jsonPath("$.address").value("Address"))
                .andExpect(jsonPath("$.email").value("vendor@example.com"))
                .andExpect(jsonPath("$.contact").value("123456789"));
    }
}

package com.cancodevery.ecom.vendor;

import com.cancodevery.ecom.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/vendors")
@RequiredArgsConstructor
public class VendorController
{
    private final VendorService vendorService;

//http://localhost:8089/api/v1/vendors/getall


    @GetMapping("/")
    public List<VendorResponseDto> getAll(){
        return vendorService.getAll();
    }


    @PostMapping("/register")
    public AuthenticationResponse add(@RequestBody VendorRequestDto vendorRequestDto){

        return vendorService.register(vendorRequestDto);
    }

    @GetMapping("/{email}")
    public VendorResponseDto get(@PathVariable String email){
        return vendorService.get(email);
    }
}

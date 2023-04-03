package com.cancodevery.ecom.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController
{
    private VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/")
    public List<VendorResponseDto> getAll(){
        return vendorService.getAll();
    }

    @PostMapping("/register")
    public VendorResponseDto add(@RequestBody VendorRequestDto vendorRequestDto){

        return vendorService.register(vendorRequestDto);
    }

    @GetMapping("/{email}")
    public VendorResponseDto get(@PathVariable String email){
        return vendorService.get(email);
    }
}

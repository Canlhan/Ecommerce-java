package com.cancodevery.ecom.vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/vendors")
public class VendorController
{
    private VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/")
    public List<Vendor> getAll(){
        return vendorService.getAll();
    }

    @PostMapping("/")
    public Vendor add(@RequestBody Vendor vendor){
        return vendorService.save(vendor);
    }
}

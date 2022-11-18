package com.cancodevery.ecom.vendorproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/vendorproducts")
public class VendorProductController
{

    private VendorProductService vendorProductService;

    @Autowired
    public VendorProductController(VendorProductService vendorProductService) {
        this.vendorProductService = vendorProductService;
    }

    @GetMapping("/")
    public List<VendorProduct> getAll(){
        return vendorProductService.getAll();
    }

    @PostMapping("/")
    public VendorProduct add(@RequestBody VendorProduct vendorProduct){

       return  vendorProductService.save(vendorProduct);
    }
}

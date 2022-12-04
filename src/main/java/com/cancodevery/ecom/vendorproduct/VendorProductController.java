package com.cancodevery.ecom.vendorproduct;

import com.cancodevery.ecom.dtos.VendorProductDto;
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
    public List<VendorProductDto> getAll(){

        return vendorProductService.getAll();
    }

    @PostMapping("/")
    public VendorProductDto add(@RequestBody VendorProductDto vendorProduct){

       return  vendorProductService.save(vendorProduct);
    }
}

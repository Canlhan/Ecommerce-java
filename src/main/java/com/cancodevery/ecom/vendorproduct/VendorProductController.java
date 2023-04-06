package com.cancodevery.ecom.vendorproduct;

import com.cancodevery.ecom.dtos.VendorProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/vendorproducts")
public class VendorProductController
{

    private VendorProductService vendorProductService;

    @Autowired
    public VendorProductController(VendorProductService vendorProductService) {
        this.vendorProductService = vendorProductService;
    }

    @GetMapping("/")
    public ResponseEntity<List<VendorProductResponseDto>> getAll(){
        try {
            List<VendorProductResponseDto> vendorProductResponseDtos=vendorProductService.getAll();
            return ResponseEntity.ok(vendorProductResponseDtos);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }


    }

    @PostMapping("/")
    public ResponseEntity<VendorProductResponseDto> add(@RequestBody VendorProductRequestDto vendorProductRequestDto){
        try {
            VendorProductResponseDto vendorProductResponseDto=vendorProductService.save(vendorProductRequestDto);
            return ResponseEntity.ok(vendorProductResponseDto);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }


    }
}

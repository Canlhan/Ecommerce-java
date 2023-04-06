package com.cancodevery.ecom.vendorproduct;

import com.cancodevery.ecom.Exception.VendorProductNotFound;
import com.cancodevery.ecom.category.Category;
import com.cancodevery.ecom.category.CategoryService;
import com.cancodevery.ecom.dtos.VendorProductDto;
import com.cancodevery.ecom.dtos.VendorProductDtoConverter;
import com.cancodevery.ecom.product.Product;
import com.cancodevery.ecom.product.ProductService;
import com.cancodevery.ecom.vendor.Vendor;
import com.cancodevery.ecom.vendor.VendorDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class VendorProductManager implements VendorProductService{
    private final VendorProductDao vendorProductDao;


    private final VendorDao vendorDao;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;



    @Override
    public List<VendorProductResponseDto> getAll() {
        List<VendorProductResponseDto> vendorProductResponseDtos=new ArrayList<>();
        vendorProductDao.findAll().stream().forEach(vendorProduct -> {
            vendorProductResponseDtos.add(modelMapper.map(vendorProduct,VendorProductResponseDto.class));
        });


        return  vendorProductResponseDtos;
    }

    @Override
    public VendorProductResponseDto get(int id) {
        VendorProduct vendorProduct=vendorProductDao.findById(id).orElseThrow(()-> new VendorProductNotFound("VendorProduct not found"));
        return modelMapper.map(vendorProduct,VendorProductResponseDto.class) ;
    }

    @Override
    public VendorProductResponseDto save(VendorProductRequestDto vendorProductRequestDto) {



        Product product=productService.save(vendorProductRequestDto.getProduct());
        System.out.println(" product saved"+product.getProductName());
        log.info("product saved",product.getProductName());

        Vendor vendor=vendorDao.findVendorByEmail(vendorProductRequestDto.getVendor().getEmail()).orElseThrow(()->
                new VendorProductNotFound("Vendor not found in vendorProductManager"));

        VendorProduct vendorProductsaved=modelMapper.map(vendorProductRequestDto,VendorProduct.class);
        vendorProductsaved.setVendor(vendor);
        vendorProductsaved.setProduct(product);
        log.info("vendorProductRequestDto ",vendorProductsaved.getProduct().getProductName());
        VendorProduct vendorProduct1=vendorProductDao.save(vendorProductsaved);
        System.out.println(vendorProduct1.getProduct().getProductName());
        log.error("vendorProductRequestDto saved",vendorProduct1);
        VendorProductResponseDto vendorProductResponseDto=modelMapper.map(vendorProduct1,VendorProductResponseDto.class);
        System.out.println(vendorProductResponseDto.getProduct().getProductName());
        return modelMapper.map(vendorProduct1,VendorProductResponseDto.class);


        /*
        Category category=categoryService.findById(vendorProductRequestDto.getCategoryId());
        Vendor  vendor=vendorDao.findById(vendorProductRequestDto.getVendorId()).get();


        Product product=new Product();
        product.setProductName(vendorProductRequestDto.getProductName());
        product.setProductPhoto(vendorProductRequestDto.getProductPhoto());
        product.setCategory(category);
        product.setProductPhoto(vendorProductRequestDto.getProductPhoto());
        product.setState(true);
        product.setUnitInStock(vendorProductRequestDto.getUnitInStock());
        product.setUnitPrice(vendorProductRequestDto.getUnitPrice());


        vendorProductsaved.setVendor(vendor);
        vendorProductsaved.setProduct(product);

        vendorProductsaved=vendorProductDao.save(vendorProductsaved);
        product.setVendorProduct(vendorProductsaved);


         */


    }
}

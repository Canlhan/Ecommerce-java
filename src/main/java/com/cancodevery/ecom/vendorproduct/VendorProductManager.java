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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
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
    public VendorProductResponseDto save(VendorProductRequestDto vendorProduct) {

        Product product=productService.save(vendorProduct.getProduct());
        vendorProduct.setProduct(product);
        VendorProduct vendorProductsaved=modelMapper.map(vendorProduct,VendorProduct.class);

        return modelMapper.map(vendorProductDao.save(vendorProductsaved),VendorProductResponseDto.class);


        /*
        Category category=categoryService.findById(vendorProduct.getCategoryId());
        Vendor  vendor=vendorDao.findById(vendorProduct.getVendorId()).get();


        Product product=new Product();
        product.setProductName(vendorProduct.getProductName());
        product.setProductPhoto(vendorProduct.getProductPhoto());
        product.setCategory(category);
        product.setProductPhoto(vendorProduct.getProductPhoto());
        product.setState(true);
        product.setUnitInStock(vendorProduct.getUnitInStock());
        product.setUnitPrice(vendorProduct.getUnitPrice());


        vendorProductsaved.setVendor(vendor);
        vendorProductsaved.setProduct(product);

        vendorProductsaved=vendorProductDao.save(vendorProductsaved);
        product.setVendorProduct(vendorProductsaved);


         */


    }
}

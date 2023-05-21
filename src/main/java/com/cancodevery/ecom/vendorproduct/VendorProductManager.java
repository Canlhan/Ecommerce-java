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

        log.info("product saved",product.getProductName());

        Vendor vendor=vendorDao.findVendorByEmail(vendorProductRequestDto.getVendor().getEmail()).orElseThrow(()->
                new VendorProductNotFound("Vendor not found in vendorProductManager"));

        VendorProduct vendorProductsaved=modelMapper.map(vendorProductRequestDto,VendorProduct.class);
        vendorProductsaved.setVendor(vendor);
        vendorProductsaved.setProduct(product);
        log.info("vendorProductRequestDto ",vendorProductsaved.getProduct().getProductName());
        VendorProduct vendorProduct1=vendorProductDao.save(vendorProductsaved);

        log.error("vendorProductRequestDto saved",vendorProduct1);


        return modelMapper.map(vendorProduct1,VendorProductResponseDto.class);
    }

    @Override
    public List<VendorProductResponseDto> getVendorProductsByVendorId(int vendorId) {

        Vendor vendor=vendorDao.findById(vendorId).orElseThrow(()-> new VendorProductNotFound("Vendor not found"));
        List<VendorProduct> vendorProducts=vendorProductDao.findVendorProductsByVendorId(vendorId);

        return vendorProducts.stream().map(vendorProduct -> modelMapper.map(vendorProduct,VendorProductResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<VendorProductResponseDto> getVendorProductsByCategoryId(int categoryId) {

        List<VendorProduct> vendorProducts=vendorProductDao.findVendorProductsByProduct_Category_Id(categoryId);
        return vendorProducts.stream().map(vendorProduct -> modelMapper.map(vendorProduct,VendorProductResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public VendorProductResponseDto update(VendorProductRequestDto vendorProduct, int id) {
        VendorProduct vendorProduct1=vendorProductDao.findById(id).orElseThrow(()-> new VendorProductNotFound("VendorProduct not found"));
        vendorProduct1.setQuantity(vendorProduct.getQuantity());
        vendorProductDao.save(vendorProduct1);
        return modelMapper.map(vendorProduct1,VendorProductResponseDto.class);
    }
}

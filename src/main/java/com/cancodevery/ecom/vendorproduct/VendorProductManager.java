package com.cancodevery.ecom.vendorproduct;

import com.cancodevery.ecom.category.Category;
import com.cancodevery.ecom.category.CategoryService;
import com.cancodevery.ecom.dtos.VendorProductDto;
import com.cancodevery.ecom.dtos.VendorProductDtoConverter;
import com.cancodevery.ecom.product.Product;
import com.cancodevery.ecom.product.ProductService;
import com.cancodevery.ecom.vendor.Vendor;
import com.cancodevery.ecom.vendor.VendorDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorProductManager implements VendorProductService{
    private final VendorProductDao vendorProductDao;
    private final VendorProductDtoConverter vendorProductDtoConverter;

    private final VendorDao vendorDao;
    private final ProductService productService;
    private final CategoryService categoryService;

    public VendorProductManager(VendorProductDao vendorProductDao,VendorProductDtoConverter vendorProductDtoConverter, VendorDao vendorDao
    ,ProductService productService,CategoryService categoryService) {
        this.vendorProductDao = vendorProductDao;
        this.vendorProductDtoConverter=vendorProductDtoConverter;
        this.vendorDao=vendorDao;
        this.productService=productService;
        this.categoryService=categoryService;
    }

    @Override
    public List<VendorProductDto> getAll() {


        return vendorProductDao.findAll().stream().map(vendorProductDtoConverter::convert).collect(Collectors.toList());
    }

    @Override
    public VendorProductDto get(int id) {
        return vendorProductDtoConverter.convert(vendorProductDao.findById(id).get()) ;
    }

    @Override
    public VendorProductDto save(VendorProductDto vendorProduct) {

        VendorProduct vendorProductsaved=new VendorProduct();
        vendorProductsaved.setDescription(vendorProduct.getDescription());
        vendorProductsaved.setPrice(vendorProduct.getPrice());
        vendorProductsaved.setQuantity(vendorProduct.getQuantity());

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


        productService.save(product);

        return vendorProductDtoConverter.convert(vendorProductsaved) ;
    }
}

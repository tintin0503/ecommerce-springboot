package com.dev.ecommerce.service;

import com.dev.ecommerce.entity.Product;
import com.dev.ecommerce.payload.ProductDto;
import com.dev.ecommerce.payload.response.ProductResponse;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    ProductDto addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy,
        String sortOrder);

    ProductDto updateProduct(Long productId, Product product);

    ProductDto updateProductImage(Long productId, MultipartFile image) throws IOException;

    ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy,
        String sortOrder);

    String deleteProduct(Long productId);

}

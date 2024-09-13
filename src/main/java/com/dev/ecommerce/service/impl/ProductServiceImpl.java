package com.dev.ecommerce.service.impl;

import com.dev.ecommerce.entity.Product;
import com.dev.ecommerce.payload.ProductDto;
import com.dev.ecommerce.payload.response.ProductResponse;
import com.dev.ecommerce.service.ProductService;
import jakarta.transaction.Transactional;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductDto addProduct(Long categoryId, Product product) {
        return null;
    }

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }

    @Override
    public ProductDto updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public ProductDto updateProductImage(Long productId, MultipartFile image) throws IOException {
        return null;
    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }

    @Override
    public String deleteProduct(Long productId) {
        return null;
    }
}

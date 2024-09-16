package com.dev.ecommerce.controllers.admin;

import com.dev.ecommerce.entity.Product;
import com.dev.ecommerce.payload.ProductDto;
import com.dev.ecommerce.service.ProductService;
import com.dev.ecommerce.util.Constants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Admin Product", description = "Product APIs for Admin")
@RestController
@RequestMapping(Constants.API + Constants.ADMIN_PATH)
@SecurityRequirement(name = Constants.SECURITY_NAME)
public class ProductAdminController {

    @Autowired
    private ProductService productService;

    @PostMapping("/categories/{categoryId}/product")
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody Product product, @PathVariable Long categoryId) {

        ProductDto savedProduct = productService.addProduct(categoryId, product);

        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody Product product, @PathVariable Long productId) {
        ProductDto updatedProduct = productService.updateProduct(productId, product);

        return new ResponseEntity<ProductDto>(updatedProduct, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}/image")
    public ResponseEntity<ProductDto> updateProductImage(@PathVariable Long productId, @RequestParam("image") MultipartFile image) throws
        IOException {
        ProductDto updatedProduct = productService.updateProductImage(productId, image);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProductByCategory(@PathVariable Long productId) {
        String status = productService.deleteProduct(productId);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }

}

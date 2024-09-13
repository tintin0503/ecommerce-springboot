package com.dev.ecommerce.controllers.admin;

import com.dev.ecommerce.service.ProductService;
import com.dev.ecommerce.util.Constants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API + Constants.ADMIN_PATH)
@SecurityRequirement(name = Constants.SECURITY_NAME)
public class ProductController {

    @Autowired
    private ProductService productService;


}

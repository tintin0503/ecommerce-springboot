package com.dev.ecommerce.controllers;

import com.dev.ecommerce.util.Constants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API)
@SecurityRequirement(name = Constants.SECURITY_NAME)
public class AuthController {
}

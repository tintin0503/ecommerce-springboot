package com.dev.ecommerce.service.impl;

import com.dev.ecommerce.entity.Cart;
import com.dev.ecommerce.entity.CartItem;
import com.dev.ecommerce.entity.User;
import com.dev.ecommerce.exception.ApiException;
import com.dev.ecommerce.exception.ResourceNotFoundException;
import com.dev.ecommerce.payload.CartDto;
import com.dev.ecommerce.payload.ProductDto;
import com.dev.ecommerce.payload.UserDto;
import com.dev.ecommerce.payload.UserResponse;
import com.dev.ecommerce.repositories.RoleRepository;
import com.dev.ecommerce.repositories.UserRepository;
import com.dev.ecommerce.service.CartService;
import com.dev.ecommerce.service.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private CartService cartService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto registerUser(UserDto userDto) {

        try {
            User user = modelMapper.map(userDto, User.class);

            Cart cart = new Cart();
            user.setCart(cart);

            User registerUser = userRepo.save(user);

            cart.setUser(registerUser);

            userDto = modelMapper.map(registerUser, UserDto.class);

            return userDto;
        } catch (DataIntegrityViolationException e) {
            throw new ApiException("User already exists with emailId: " + userDto.getEmail());
        }
    }

    @Override
    public UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        Page<User> pageUsers = userRepo.findAll(pageDetails);

        List<User> users = pageUsers.getContent();

        if (users.isEmpty()) {
            throw new ApiException("No User Exist !!!");
        }

        List<UserDto> userDtos = users.stream().map(user -> {
            UserDto dto = modelMapper.map(user, UserDto.class);

            CartDto cart = modelMapper.map(user.getCart(), CartDto.class);

            List<ProductDto> products = user.getCart().getCartItems().stream()
                .map(item -> modelMapper.map(item.getProduct(), ProductDto.class)).toList();

            dto.setCartDto(cart);

            dto.getCartDto().setProducts(products);

            return dto;
        }).toList();

        UserResponse userResponse = new UserResponse();

        userResponse.setContent(userDtos);
        userResponse.setPageNumber(pageUsers.getNumber());
        userResponse.setPageSize(pageUsers.getSize());
        userResponse.setTotalElements(pageUsers.getTotalElements());
        userResponse.setTotalPages(pageUsers.getTotalPages());
        userResponse.setLastPage(pageUsers.isLast());

        return userResponse;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        UserDto userDto = modelMapper.map(user, UserDto.class);

        CartDto cart = modelMapper.map(user.getCart(), CartDto.class);

        List<ProductDto> productDtos = user.getCart().getCartItems().stream()
            .map(item -> modelMapper.map(item.getProduct(), ProductDto.class)).toList();

        userDto.setCartDto(cart);
        userDto.getCartDto().setProducts(productDtos);

        return userDto;
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDTO) {

        User user = userRepo.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        String encodedPass = passwordEncoder.encode(user.getPassword());

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setMobileNumber(userDTO.getMobileNumber());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encodedPass);

        userDTO = modelMapper.map(user, UserDto.class);

        CartDto cart = modelMapper.map(user.getCart(), CartDto.class);

        List<ProductDto> products = user.getCart().getCartItems().stream()
            .map(item -> modelMapper.map(item.getProduct(), ProductDto.class)).collect(Collectors.toList());

        userDTO.setCartDto(cart);

        userDTO.getCartDto().setProducts(products);

        return userDTO;
    }

    @Override
    public String deleteUser(Long userId) {
        User user = userRepo.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        List<CartItem> cartItems = user.getCart().getCartItems();
        Long cartId = user.getCart().getCartId();

        cartItems.forEach(item -> {
            Long productId = item.getProduct().getProductId();
            cartService.deleteProductFromCart(cartId, productId);
        });

        userRepo.delete(user);

        return "User with userId " + userId + " deleted successfully!!!";
    }
}

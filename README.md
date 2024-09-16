# E-commerce-Application API

- The E-Commerce Application is built using Java and Spring Boot, with security, scalability, and ease of maintenance. 
- The backend uses Spring Data JPA to interact with a MySQL database, making it easy to manage and store important entities such as users, products, categories, orders, and more. 
- User authentication is handled by Auth0, providing secure and reliable means of REST APIs.
- The APIs are well-documented and easily accessible through Swagger UI, making it simple for developers to test and understand the various endpoints. 
- Overall, this project provides secure Rest APIs to create a scalable platform for businesses to sell their products to customers.

# Features
  ## Login for Admin, register and login for User:
  - ```api/login``` **POST**
  - ```api/register``` **POST**
  ## Admin:
  - Get all users: ```/api/admin/users``` **GET**
  - Delete user by id: ```/api/admin/users/{userId}``` **DELETE**
  - Category:
    - Create: ```/api/admin/category``` **POST**
    - Update: ```/api/admin/categories/{categoryId}``` **PUT**
    - Delete: ```/api/admin/categories/{categoryId}``` **DELETE**
  - Product:
    - Create: ```/api/admin/categories/{categoryId}/product``` **POST**
    - Update Product: ```/api/admin/products/{productId}``` **PUT**
    - Update Product image: ```/api/admin/products/{productId}/image``` **PUT**
    - Delete Product: ```/api/admin/products/{productId}``` **DELETE**
  - Price & discount: ...
  - Orders: ...

  ## User:
  - Get detail user: ```/api/internal/public/users/{userId}``` **GET**
  - Update user: ```/api/internal/public/users/{userId}``` **PUT**
  - Fetch categories and products based on category: ...
  - Adding & deleting products to cart: ...
  - Managing address and products quantity: ...
  - Ordering products and fetching order status: ...

# Security
- The API is secured using JSON Web Tokens (JWT) handled by Auth0. To access the API, you will need to obtain a JWT by authenticating with the /login endpoint. The JWT should then be passed in the Authorize option available in the Swagger-ui.
  ### Example:
  - Authorization: <your_jwj>

# Technologies:
- Java 17 or above
- Spring Boot 3.0
- Maven
- MySQL
- Spring Data JPA
- Spring Security
- JSON Web Tokens (JWT)
- Auth0
- Swagger UI

# API documentation
- API documentation is available via Swagger UI at http://localhost:8080/swagger-ui/index.html

# Thank you.

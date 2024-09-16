# E-commerce-Application API

- The E-Commerce Application is built using Java and Spring Boot, with security, scalability, and ease of maintenance. 
- The backend uses Spring Data JPA to interact with a MySQL database, making it easy to manage and store important entities such as users, products, categories, orders, and more. 
- User authentication is handled by Auth0, providing secure and reliable means of REST APIs.
- The APIs are well-documented and easily accessible through Swagger UI, making it simple for developers to test and understand the various endpoints. 
- Overall, this project provides secure Rest APIs to create a scalable platform for businesses to sell their products to customers.

# Features
  ## Admin:
    - Login
    - Users
    - Categories
    - Products
    - Price & discount
    - Orders

  ## User:
    - Registration & Login
    - Fetch categories and products based on category
    - Adding & deleting products to cart
    - Managing address and products quantity
    - Ordering products and fetching order status

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

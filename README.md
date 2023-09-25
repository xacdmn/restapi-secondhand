# API Documentation - SecondHand Project

## Introduction
This is the documentation for the SecondHand project, a web application for transacting used goods. The project utilizes Spring Boot, Java 8, Maven, and other technologies to deliver a robust and efficient API.

## Prerequisites
1. Java 8
2. Maven
3. Spring Boot

## Getting Started
1. Clone the repository.
2. Navigate to the directory where you've cloned the repository.
3. Run the application using:

   ```bash
   mvn spring-boot:run
   ```

4. The application will be running at `http://localhost:8080`.

## API Documentation with Swagger
For a more detailed understanding and testing of each endpoint, you can utilize the provided Swagger documentation. Swagger offers an easy-to-use interface to view all available endpoints, their data models, and to perform request testing.

**Swagger Link**: [http://localhost:8080/swagger-ui/index.html#](http://localhost:8080/swagger-ui/index.html#)

## Controllers & Endpoints

### 1. History Controller
- **Find All Products by User**:
  - Method: `GET`
  - Endpoint: `/api/history/product-user`
  
- **Find Wishlist Products by User**:
  - Method: `GET`
  - Endpoint: `/api/history/product-wishlist`

- **Find Sold Products by User**:
  - Method: `GET`
  - Endpoint: `/api/history/product-sold`

### 2. Homepage Controller
- **Find Product by Product ID**:
  - Method: `GET`
  - Endpoint: `/api/homepage/{productId}`

- **Display All Products (not for public)**:
  - Method: `GET`
  - Endpoint: `/api/homepage/show-products`

- **List All Categories**:
  - Method: `GET`
  - Endpoint: `/api/homepage/list-category`

- **Retrieve Products with Sorting and Filtering**:
  - Method: `GET`
  - Endpoint: `/api/homepage/get-product`

### 3. Notification Controller
- **List Notifications by User**:
  - Method: `GET`
  - Endpoint: `/api/notification/get`

- **Mark Notification as Read**:
  - Method: `PUT`
  - Endpoint: `/api/notification/read/{id}`

### 4. Offer Controller
- **API for WhatsApp (by accepted status)**:
  - Method: `GET`
  - Endpoint: `/api/offer/show-offer/whatsapp/{offerId}`

- **Show Offer by Buyer**:
  - Method: `GET`
  - Endpoint: `/api/offer/show-offer/{offerId}`

- **Add Offers**:
  - Method: `POST`
  - Endpoint: `/api/offer/add/{productId}`

- **Update Offer Status (Accepted/Rejected)**:
  - Method: `PUT`
  - Endpoint: `/api/offer/update/{offerId}/{status}`

- **Update Offer Status (Sold/NotSold)**:
  - Method: `PUT`
  - Endpoint: `/api/offer/update/isSold/{offerId}/{status}`

### 5. Product Controller
- **Validation Profile Endpoint**:
  - Method: `GET`
  - Endpoint: `/api/product/sell`

- **Find Product by ID**:
  - Method: `GET`
  - Endpoint: `/api/product/{productId}`

- **Preview or Publish Product**:
  - Method: `POST`
  - Endpoint: `/api/product/add/{isPublished}`

- **Publish Product After Preview**:
  - Method: `PUT`
  - Endpoint: `/api/product/update/publish/{productId}`

- **Edit Product by ID**:
  - Method: `PUT`
  - Endpoint: `/api/product/update/{productId}`

- **Delete Product by ID**:
  - Method: `DELETE`
  - Endpoint: `/api/product/delete/{productId}`

### 6. UserController
- **Get Current User**:
  - Method: `GET`
  - Endpoint: `/api/user/current`

- **Get Current User Details**:
  - Method: `GET`
  - Endpoint: `/api/user/current-detail`

- **Update User Profile**:
  - Method: `PUT`
  - Endpoint: `/api/user/update`

- **Change User Password**:
  - Method: `PUT`
  - Endpoint: `/api/user/change-password`

## Conclusion
This documentation provides a comprehensive overview of the SecondHand project's API functionalities. The Swagger documentation can provide further in-depth details. We hope you find this API both easy to navigate and purposeful for your needs.

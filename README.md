# E-Commerce API Project

## Overview
Simple e-commerce  structure built using RESTful APIs. It provides a comprehensive backend system for managing products, users, and more. 
The application is developed with Spring Boot and Java Language

## Technologies Used
- **Spring Boot**: Framework for building the application.
- **Spring MVC**: For handling the web layer and RESTful endpoints.
- **Spring Security**: For securing the application with JWT-based authentication.
- **Spring Data JPA**: For data persistence and repository management.
- **Flyway DB**: For managing database migrations.
- **Lombok**: To reduce boilerplate code.
- **Auth0-JWT**: To generate standard JWT tokens
- **Spring Doc**: For generating swagger documentation
- **Spring Eureka Server**: For Service discovering
- **Spring Server Client**: To comunnicate with the server and client load balance
- **RabbitMQ**: To communicate and enqueue emails 
- **Gmail**: Used as SMTP to send emails



## Features

- **User Authentication and Authorization**: Uses JWT to secure endpoints.
- **Role-based Access Control**: Three different user roles (Customer, Merchant, Admin) with specific permissions and routes.
- **Database Migrations**: Managed by Flyway DB.
- 
## Rules
- **Non logged users**: 
  - Show a complete list of products with pagination function
  - Show products by category 
  - Search products by name or description
  
- **Signup**: 
  - Users can register on the platform using email/password and choose between different roles (Customer, Merchant or Admin)

- **Customer**: 
  - Customer profile, with name and document
  - Multiples addresses
  - Multiples credit cards
  - Add products into a Cart
  - Create an Order based on products previously included in the cart

- **Merchant**: 
  - Merchant Profile, with name and document 
  - CRUD products within the already existing categories

- **Admin**: 
  - CRUD Products, Categories and Users.
- **Cart**
    - Carts are created and maintained by Customers. Each customer can have only one Cart, with different products 
- **Orders**
  - Orders register all the products on specific cart, including actual price and total value of the cart.
  - To create a order is necessary an CustomerAddress and CustomerCreditCard
  - After each order, all products from the customer cart are removed 

    
  
## Getting Started
### Prerequisites
- Java 17 or higher
- MySql 


### Projectss
1. https://github.com/cesarsicas/springstore-payment-service
2. https://github.com/cesarsicas/springstore-email-service
3. https://github.com/cesarsicas/springstore-service-discovery


### Installation


## ERD

![erd_diagram](https://github.com/user-attachments/assets/608ab45c-4e7c-4213-9ca1-67bf24eee976)

## Swagger

![swagger](https://github.com/user-attachments/assets/36772be4-816b-4fea-a1e0-feba2232f32e)



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

    

## //Todo
- Email to the client and merchant after Orders creation
- Live chat


## Getting Started
### Prerequisites
- Java 17 or higher
- MySql 

### Installation
1. Clone the repository
2. Create a schema with the name **springstore_db**
3. Setup the Mysql connection on **application.properties** file


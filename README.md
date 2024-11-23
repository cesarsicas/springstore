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


## Rules (so far)
- **Signup**: Any user can register on the plataform using email and a simple password, and choose between different roles (Customer, Merchant or Admin)
- **Customer**: Customers can show a list of all products, search by name or description or filter by product category.  
- **Merchant**: Merchants can CRUD products within the already existing categories
- **Admin**: Admins can CRUD Products, Categories and Users.

## Features

- **User Authentication and Authorization**: Uses JWT to secure endpoints.
- **Role-based Access Control**: Three different user roles (Customer, Merchant, Admin) with specific permissions and routes.
- **Database Migrations**: Managed by Flyway DB.

## //Todo
- Order Management, Cart and Shopping List
- Email to the client for confirmation
- Live chat


## Getting Started
### Prerequisites
- Java 17 or higher
- MySql 

### Installation
1. Clone the repository
2. Create a schema with the name **springstore_db**
3. Setup the Mysql connection on **application.properties** file


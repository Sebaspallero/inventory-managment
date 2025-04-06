# Inventory Management System - Backend

A basic inventory management REST API built with **Spring Boot**, designed to handle product stock, supplier relationships, order processing, and manual inventory movements. This project is intended as a foundational backend system for managing inventory in small to mid-sized businesses.

## 🚀 Features

- **User Management**: Create and manage system users.
- **Product Management**: Register products with price and stock tracking.
- **Supplier Management**: Store and retrieve supplier data.
- **Order Management**: Create, view, update, and delete purchase orders linked to suppliers.
- **Order Items**: Each order includes one or more items, connected to products.
- **Inventory Movements**: Manually register `IN` (incoming) or `OUT` (outgoing) stock operations.
- **Stock Updates**: Stock levels are automatically updated when a movement is created.
- **Date-based Queries**: Retrieve orders or movements between date ranges.

## 🧠 Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- MySQL (or any SQL database)
- MapStruct
- Maven
- Lombok
- Validation API
- Junit - Mockito - Postman for testing

## 📦 Entities Overview

### Product
- Basic unit with name, price, and stock
- Connected to OrderItems and InventoryMovements

### User
- Represents a system user performing inventory actions

### Supplier
- Provides products
- Linked to orders

### Order & OrderItem
- Each order is placed to a supplier and contains multiple order items
- Items affect products but **do not update stock directly** yet

### InventoryMovement
- Manual stock movements (IN or OUT)
- Updates product stock on creation
- Linked to both Product and User

## 🧪 Sample Endpoints

- `POST /api/orders` – Create a new order
- `GET /api/orders/{id}` – Retrieve order by ID
- `POST /api/movements` – Create a manual inventory movement
- `GET /api/products` – List all products
- `GET /api/movements/product/{productId}` – Movements by product

## ⚙️ Environment Configuration

Environment variables can be injected via `application.properties`:

```properties
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Set variables in .vscode/launch.json or your environment before running the app.

## 🧰 How to Run

1. Clone the repo

2. Set up your database and configure environment variables

3. Build and run:

```bash
./mvnw spring-boot:run
```

## 📌 Next Steps

- Secure the API with Spring Security (role-based access)

- Automate inventory movements from order processing

- Add pagination and filtering

- Frontend integration

## 📫 Contact

Feel free to reach out:

- GitHub: [@Sebaspallero](https://github.com/Sebaspallero)
- Email: sebastianpallerodev@gmail.com

⭐ **Please consider giving it a star!** It helps visibility and motivates further development.
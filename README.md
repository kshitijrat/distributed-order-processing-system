# Distributed Order Processing System

A scalable Spring Boot backend that manages end-to-end order lifecycle with secure JWT-based authentication, an internal payment processing engine, and inventory management. The system demonstrates real-world transaction handling using state-machine based payment flow, retry logic, and ledger tracking.

---

## Features

* Secure JWT-based authentication (Signup/Login)
* Complete order lifecycle management (PENDING → CONFIRMED / FAILED)
* Internal payment processing engine using state-machine pattern
* Retry and fraud simulation logic for realistic transaction handling
* Transaction ledger tracking for payment audit trail
* Inventory module for stock validation and reduction
* RESTful layered architecture with clean service orchestration

---

## Tech Stack

* Java 17
* Spring Boot
* Spring Security + JWT
* Spring Data JPA (Hibernate)
* MySQL / H2 (for demo)
* Lombok
* Swagger (OpenAPI)

---

## System Workflow

User Authentication → Create Order → Payment Processing Engine (State Machine + Retry + Fraud Check) → Inventory Validation → Order Status Update → Ledger Transaction Tracking

---

## Modules

* **User Module** – Handles signup, login, and JWT authentication
* **Order Module** – Manages order creation and lifecycle states
* **Payment Engine Module** – Processes transactions with retry & fraud simulation
* **Inventory Module** – Validates and updates product stock
* **Ledger (PaymentTransaction)** – Stores all transaction attempts for audit tracking

---

## Authentication

The system uses stateless JWT authentication.
All secured APIs require:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## Running the Project Locally

### 1 Clone Repository

```bash
git clone https://github.com/kshitijrat/distributed-order-processing-system.git
cd distributed-order-processing-system
```

### 2 Configure Properties

Create `application.properties` with:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/order_system
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=your_jwt_secret_key
jwt.expiration=3600000
```

### 3 Run Application

```bash
./mvnw spring-boot:run
```

### 4 Access Swagger UI

```
http://localhost:8080/swagger-ui/index.html
```

Use Swagger to:

1. Signup user
2. Login and get JWT token
3. Authorize using Bearer token
4. Create order
5. Trigger payment engine
6. Verify order status and ledger records

---

## Core APIs

* `POST /auth/signup` – Register new user
* `POST /auth/login` – Login & get JWT token
* `POST /api/orders` – Create order
* `POST /api/payment-processor/pay` – Process payment via internal engine
* `GET /api/payment-processor/{orderId}` – View transaction ledger
* `POST /api/inventory` – Add/update stock

---

## Key Concepts Implemented

* State Machine Pattern for payment lifecycle
* Retry and failure handling logic
* Fraud simulation for realistic processing
* Transaction ledger for audit tracking
* Secure stateless authentication using JWT
* Modular layered architecture (User, Order, Payment, Inventory)

---

## Use Cases

* E-commerce order processing systems
* Fintech transaction processing workflows
* Ticket booking or food delivery backend services


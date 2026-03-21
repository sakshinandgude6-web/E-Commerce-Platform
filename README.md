#  E-Commerce Backend Platform

A production-style RESTful E-Commerce Backend built with **Java**, **Spring Boot**, **PostgreSQL**, **JWT Authentication**, and **Role-Based Access Control (RBAC)**. This project simulates a real-world backend system for an online shopping platform.

##  Live / Deployment
- Backend deployed on **AWS EC2 + AWS RDS**

##  Tech Stack

| Layer | Technologies |
|-------|-------------|
| Backend | Java 17, Spring Boot, Spring Web, Spring Data JPA, Spring Security |
| Security | JWT, BCrypt, RBAC |
| Database | PostgreSQL |
| Tools | Maven, Postman |
| Deployment | AWS EC2, AWS RDS |

##  Features

###  Authentication & Security
- User registration and login
- JWT-based authentication with expiry
- Role-Based Access Control (RBAC) — Admin / User
- Password encryption using BCrypt

###  User Management
- Register / Login
- View and manage user profiles
- Role assignment

###  Product Management
- Add, update, delete products
- View and search products

###  Cart Management
- Add/remove items, update quantities
- View cart contents

###  Order Management
- Place orders, view order history
- Manage order status

##  Project Structure
src/
├── controller/     → Handles API requests
├── service/        → Business logic
├── repository/     → Database interaction (JPA)
├── entity/         → JPA entities
├── dto/            → Data Transfer Objects
├── config/         → App configuration
├── security/       → JWT & Auth filters
└── exception/      → Custom error handling

##  API Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | `/auth/register` | Register user | Public |
| POST | `/auth/login` | Login user | Public |
| GET | `/users/profile` | Get profile | Protected |
| GET | `/products` | List products | Public |
| POST | `/products` | Create product | Admin |
| PUT | `/products/{id}` | Update product | Admin |
| DELETE | `/products/{id}` | Delete product | Admin |
| GET | `/cart` | View cart | Protected |
| POST | `/cart/add` | Add to cart | Protected |
| POST | `/orders` | Place order | Protected |
| GET | `/orders` | View orders | Protected |

##  Setup Instructions

### Prerequisites
- Java 17+, Maven, PostgreSQL, Git

### Clone & Configure
```bash
git clone https://github.com/sakshi-nandgude/E-Commerce-Platform
cd E-Commerce-Platform
```

Create and configure the database:
```sql
CREATE DATABASE ecommerce_db;
```

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
jwt.secret=your_secret_key
jwt.expiration=86400000
```

### Build & Run
```bash
mvn clean install
mvn spring-boot:run
# App runs at: http://localhost:8080
```

##  Future Improvements
- [ ] Payment gateway integration (Stripe/PayPal)
- [ ] Email notifications
- [ ] Product reviews & ratings
- [ ] Docker support
- [ ] CI/CD pipeline with GitHub Actions
- [ ] Swagger/OpenAPI documentation
- [ ] Redis caching

##  Author
**Sakshi Vijay Nandgude** — MSc Business Analytics, University of Limerick

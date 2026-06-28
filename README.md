# 🏨 Hotel Management System
A secure Hotel Management REST API built using **Spring Boot**, **Spring Security**, **OAuth2**, and **Keycloak**. This project demonstrates authentication, authorization, role-based access control (RBAC), and CRUD operations using a layered architecture.

---

## 📌 Project Overview
The Hotel Management System is a Spring Boot application that provides secure REST APIs for managing hotel information. It integrates **Keycloak** as the Identity and Access Management (IAM) solution and uses **OAuth2 Resource Server** with **JWT authentication** to secure application endpoints.

The project follows the standard **Controller → Service → Repository** architecture, making it easy to understand, maintain, and extend.

---

## 🚀 Features
- Secure REST APIs using Spring Security
- OAuth2 Authentication with Keycloak
- JWT Token Validation
- Role-Based Access Control (RBAC)
- Hotel CRUD Operations
- Spring Data JPA Integration
- MySQL Database Support
- Layered Architecture
- DTO-Based Request Handling
- Method-Level Security using `@PreAuthorize`

---

## 🛠️ Technology Stack
| Technology | Version |
|------------|----------|
| Java | 17 |
| Spring Boot | 3.3.5 |
| Spring Security | Latest |
| Spring Data JPA | Latest |
| OAuth2 Resource Server | Latest |
| Keycloak | Latest |
| Hibernate | Latest |
| MySQL | 8.x |
| Maven | Latest |
| Thymeleaf | Latest |

---

## 📂 Project Structure
```
src
├── main
│   ├── java
│   │   └── com.hotelmanagement
│   │       ├── config
│   │       ├── controller
│   │       ├── dto
│   │       ├── entity
│   │       ├── repository
│   │       ├── service
│   │       └── HotelManagementApplication
│   │
│   └── resources
│       ├── application.yml
│       ├── templates
│       └── static
│
└── test
```

---

## 🏗️ Architecture
```
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Database
```

---

## 🔐 Authentication Flow
```
Client
   │
   ▼
Spring Security
   │
   ▼
Keycloak Login
   │
   ▼
JWT Token Generation
   │
   ▼
Spring Security
   │
   ▼
Protected REST APIs
```

---

## 👥 Roles & Authorization

| Role | Permissions |
|------|-------------|
| ADMIN | Create, View, Delete Hotels |
| NORMAL | View Hotels |

Method-level authorization is implemented using:
```java
@PreAuthorize("hasRole('ADMIN')")
```

---

## 📡 REST API Endpoints
### Create Hotel
```
POST /hotel/create
```

### Get All Hotels
```
GET /hotel/getAll
```

### Get Hotel by ID
```
GET /hotel/id/{id}
```

### Delete Hotel
```
DELETE /hotel/remove/id/{id}
```

---

## 💾 Database
The application uses **MySQL** with **Spring Data JPA** and **Hibernate** for database operations.

---

## ⚙️ Configuration
Configure the following in `application.yml`:
- Database URL
- Database Username
- Database Password
- Keycloak Realm
- Client ID
- Client Secret
- OAuth2 Issuer URI

> **Note:** For production environments, sensitive information such as database credentials and client secrets should be stored using environment variables or a secure secret management solution.

---
## ▶️ Running the Application
### Clone the Repository
```bash
git clone https://github.com/lintez2002/hotel-management.git
```
### Navigate to the Project
```bash
cd hotel-management
```
### Build the Project
```bash
mvn clean install
```
### Run the Application
```bash
mvn spring-boot:run
```
The application will start on:
```
http://localhost:8082
```
---

## 📚 Key Concepts Demonstrated
- Spring Boot REST API Development
- Spring Security
- OAuth2 Authentication
- Keycloak Integration
- JWT Authentication
- Role-Based Authorization
- Spring Data JPA
- Hibernate ORM
- Dependency Injection
- Layered Architecture
- DTO Pattern
- Repository Pattern

---

## 🔮 Future Enhancements
- Hotel Booking Module
- Room Management
- Customer Management
- Reservation Management
- Payment Integration
- Email Notifications
- Swagger/OpenAPI Documentation
- Docker Support
- Unit & Integration Testing
- Global Exception Handling
- Logging with SLF4J
- Pagination and Sorting

---

## 👨‍💻 Author
**Teja Lingabothu**
- GitHub: https://github.com/lintez2002
  
---

## ⭐ Support
If you found this project helpful, consider giving it a ⭐ on GitHub.
Feedback and contributions are always welcome.

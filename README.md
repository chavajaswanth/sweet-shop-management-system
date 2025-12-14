# Sweet Shop Management System (Backend)

A secure and scalable backend REST API for managing a sweet shop, built using **Java, Spring Boot, JWT authentication, and MySQL**, following **Test Driven Development (TDD)** practices.

---

##  Features

###  Authentication & Authorization
- User registration and login
- JWT-based authentication
- Role-based access control (`USER`, `ADMIN`)
- Stateless security using Spring Security

###  Sweet Management (Protected APIs)
- Add a new sweet
- View all available sweets
- Search sweets by name
- Update sweet details
- Delete a sweet (**Admin only**)

###  Inventory Management
- Purchase sweets (reduces stock)
- Restock sweets (**Admin only**)

###  Database
- Initially developed using **H2**
- Migrated to **MySQL** for production readiness
- JPA & Hibernate ORM

---

##  Tech Stack

- **Java 17**
- **Spring Boot 3**
- **Spring Security + JWT**
- **MySQL**
- **Spring Data JPA / Hibernate**
- **JUnit 5 & MockMvc**
- **Maven**

---

##  Testing

- Test Driven Development (TDD)
- RED → GREEN → REFACTOR workflow
- Unit tests for controllers, security, and JWT
- Security tests for unauthorized and admin-only access

---

## API Endpoints

### Auth (Public)
POST /api/auth/register
POST /api/auth/login



### Sweets (JWT Protected)
POST /api/sweets
GET /api/sweets
GET /api/sweets/search?name=lad
PUT /api/sweets/{id}
DELETE /api/sweets/{id} (ADMIN only)



### Inventory (JWT Protected)
POST /api/sweets/{id}/purchase?quantity=5
POST /api/sweets/{id}/restock?quantity=10 (ADMIN only)



---

## Security Design

- Stateless authentication
- JWT contains `username` and `role`
- Custom JWT authentication filter
- Method-level security using `@PreAuthorize`
- Unauthorized access → **401**
- Forbidden access → **403**

---

## Configuration

### `application.properties`

spring.datasource.url=jdbc:mysql://localhost:3306/sweetshop
spring.datasource.username=sweetuser
spring.datasource.password=Sweet@123

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false

---

##  How to Run

### Prerequisites
- Java 17+
- Maven
- MySQL running

### Steps
git clone https://github.com/chavajaswanth/sweetshop.git
cd sweetshop
mvn clean spring-boot:run



Application runs at:
http://localhost:8080



---

##  Testing with Postman

1. Login to get JWT token:
POST /api/auth/login



2. Copy the token from response

3. Add Header in protected APIs:
Authorization: Bearer <JWT_TOKEN>


---

##  Highlights

- Clean layered architecture
- Industry-standard JWT security
- Role-based authorization
- MySQL migration completed
- Production-ready backend
- TDD-driven development

---

##  Author

**Jaswanth Chava**  
Backend Developer | Java | Spring Boot  
GitHub: https://github.com/chavajaswanth

---

## Future Improvements
- Password encryption (BCrypt)
- Pagination and sorting
- Flyway database migrations
- Docker support
- Frontend integration

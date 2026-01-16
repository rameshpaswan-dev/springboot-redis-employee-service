🚀 Employee Management System using Spring Boot & Redis

A RESTful backend application built with Spring Boot and Redis to perform CRUD operations on Employee data.
This project demonstrates how Redis can be used as a fast in-memory data store without using Spring Data JPA.

📌 Features

Create, Read, Update, Delete (CRUD) Employee records

Redis used as primary data store (Hash-based storage)

Clean layered architecture (Controller → Service → Redis)

Generic API response structure

UUID-based employee ID generation

No database / No JPA – Redis only

RESTful APIs following best practices

🏗️ Project Architecture
┌────────────────────┐
│  Client (Postman)  │
└─────────▲──────────┘
          │ HTTP Requests
          ▼
┌────────────────────────┐
│  EmployeeController    │
│  (REST APIs)           │
└─────────▲──────────────┘
          │
          ▼
┌────────────────────────┐
│  EmployeeService       │
│  Business Logic        │
└─────────▲──────────────┘
          │
          ▼
┌────────────────────────┐
│  RedisTemplate         │
│  (Hash Operations)     │
└─────────▲──────────────┘
          │
          ▼
┌────────────────────────┐
│  Redis Server          │
│  In-Memory Data Store  │
└────────────────────────┘

🔹 Flow Explanation

Client sends REST request

Controller handles request

Service layer applies business logic

Data is stored/retrieved from Redis using RedisTemplate

Response is wrapped in a standard ApiResponse

🧰 Tech Stack
Technology	Description
Java	Java 17+
Spring Boot	4.0.1
Spring Web	REST API development
Spring Data Redis	Redis integration
Redis	In-memory data store
Lombok	Reduce boilerplate code
Maven	Dependency management
Postman	API testing
📂 Redis Data Structure Used

Key: EMPLOYEE

Type: Redis Hash

Hash Key: Employee ID (UUID)

Value: Employee Object (JSON)

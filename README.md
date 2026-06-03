# 🎬 Movie Rental REST API

A backend REST API for a Movie Rental platform built with **Java Spring Boot** as part of the OOP module at **SLIIT**. The system handles movies, users, rentals, reviews, ratings, and more — following clean OOP principles with a layered architecture.

---

## 🛠️ Tech Stack

| Technology | Details |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.0.3 |
| Database | MySQL |
| ORM | Spring Data JPA / Hibernate |
| Security | Spring Security |
| Build Tool | Gradle (Kotlin DSL) |
| API Docs | SpringDoc OpenAPI (Swagger UI) |
| Utilities | Lombok, Bean Validation |

---

## 📁 Project Structure

```
src/main/java/sliit/oop_server_app/
│
├── config/          # CORS, Security & Password configuration
├── controller/      # REST API endpoints
├── Service/         # Business logic layer
├── repository/      # JPA repositories (data access layer)
├── entity/          # JPA entities (database models)
├── DTO/             # Data Transfer Objects
└── exception/       # Global exception handling
```

---

## 🚀 API Endpoints

### 🎥 Movies `/movies`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/movies/all` | Get all movies |
| GET | `/movies/{id}` | Get movie by ID |
| GET | `/movies/trending` | Get movies sorted by ratings |
| GET | `/movies/search?query=` | Search movies by name |
| GET | `/movies/filter/imdb/{imdb}` | Filter movies by IMDB rating |
| POST | `/movies/create` | Add a new movie |
| PUT | `/movies/update/{id}` | Update movie details |
| PUT | `/movies/update/count/{id}` | Increment view count |
| DELETE | `/movies/delete/{id}` | Delete a movie |

---

### 👤 Users `/users`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/users` | Get all users |
| POST | `/users/save` | Register a new user |
| PUT | `/users/update` | Update user info |
| DELETE | `/users/delete/{id}` | Delete a user |

---

### 🔐 Auth `/auth`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/login` | User login |

---

### 🎞️ Rentals `/rentals`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/rentals` | Get all rentals |
| GET | `/rentals/{id}` | Get rentals by user ID |
| POST | `/rentals/save` | Add a new rental |
| DELETE | `/rentals/delete/{id}` | Delete a rental |

---

### ⭐ Reviews `/reviews`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/reviews` | Get all reviews |
| GET | `/reviews/{id}` | Get reviews by movie ID |
| GET | `/reviews/user/{id}` | Get reviews by user ID |
| GET | `/reviews/bestfive` | Get top 5 reviews by likes |
| POST | `/reviews/save` | Add a new review |
| PUT | `/reviews/update` | Update a review |
| PUT | `/reviews/like/{id}` | Like a review |
| PUT | `/reviews/unlike/{id}` | Unlike a review |
| DELETE | `/reviews/delete/{id}` | Delete a review |

---

### 💬 Review Replies `/reviews/reply`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/reviews/reply` | Add a reply to a review |
| GET | `/reviews/reply/{id}` | Get replies for a review |
| PUT | `/reviews/reply/edit` | Edit a reply |
| POST | `/reviews/reply/viewed/{id}` | Mark reply as viewed |
| DELETE | `/reviews/reply/delete/{id}` | Delete a reply |

---

### 🎭 Actors `/actors`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/actors` | Get all actors |
| POST | `/actors/save` | Add a new actor |
| DELETE | `/actors/delete/{id}` | Delete an actor |

---

### 🗂️ Categories `/categories`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/categories` | Get all categories |
| POST | `/categories/save` | Add a new category |
| DELETE | `/categories/delete/{id}` | Delete a category |

---

## ⚙️ Getting Started

### Prerequisites

- Java 17+
- MySQL 8+
- Gradle

### 1. Clone the repository

```bash
git clone https://github.com/your-username/OOP_Server_App.git
cd OOP_Server_App
```

### 2. Configure the database

Open `src/main/resources/application.properties` and update your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/oop_db?serverTimezone=UTC
spring.datasource.username=your_username
spring.datasource.password=your_password
```

> **Note:** Make sure to create a database named `oop_db` in MySQL before running.

### 3. Run the application

```bash
./gradlew bootRun
```

The server will start on **port 9878**.

---

## 📖 API Documentation

Once the app is running, visit the Swagger UI at:

```
http://localhost:9878/swagger-ui/index.html
```

---

## 🗃️ Database Schema (Entities)

| Entity | Description |
|---|---|
| `Movie` | Movie details including name, language, IMDB rating, price, trailer link |
| `User` | Registered users |
| `Rental` | Records of movies rented by users |
| `Review` | User reviews with likes/dislikes |
| `Reply` | Replies to reviews |
| `Actor` | Actor profiles |
| `Category` | Movie categories |
| `ActorsHasMovie` | Many-to-many: Actors ↔ Movies |
| `CategoryHasMovie` | Many-to-many: Categories ↔ Movies |

---



> Built as part of the Object-Oriented Programming (OOP) module at Sri Lanka Institute of Information Technology (SLIIT).

---

## 📄 License

This project is for educational purposes as part of the SLIIT OOP module.

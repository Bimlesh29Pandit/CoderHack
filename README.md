# CoderHack# 🧠 CoderHack Leaderboard API

A RESTful API service built with **Spring Boot** and **MongoDB** to manage a coding platform's leaderboard for a single contest. Users can register, update their scores, and earn badges based on performance.

---

## 🚀 Features

- ✅ User Registration (with unique ID and username)
- 📊 Score Update with Auto Badge Assignment
- 🏅 Badge System:
  - `Code Ninja` (Score ≥ 1)
  - `Code Champ` (Score ≥ 30)
  - `Code Master` (Score ≥ 60)
- 📋 Sorted Leaderboard by Score (Descending)
- ⚙️ CRUD Operations
- 🧪 Basic JUnit Test Cases
- 📦 MongoDB Integration
- 🧱 Layered Architecture: `Entity`, `Repository`, `Service`, `Controller`

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot 3+
- Spring Web
- Spring Data MongoDB
- Lombok
- JUnit
- Postman
- MongoDB

---

## 📦 API Endpoints

| Method | Endpoint          | Description                 |
| ------ | ----------------- | --------------------------- |
| GET    | `/users`          | Retrieve all users (sorted) |
| GET    | `/users/{userId}` | Get a user by ID            |
| POST   | `/users`          | Register a new user         |
| PUT    | `/users/{userId}` | Update user score           |
| DELETE | `/users/{userId}` | Delete a user               |

---

## 🔐 Badge Assignment Rules

| Score Range      | Badge       |
| ---------------- | ----------- |
| 1 ≤ Score < 30   | Code Ninja  |
| 30 ≤ Score < 60  | Code Champ  |
| 60 ≤ Score ≤ 100 | Code Master |

> ⚠️ Max 3 unique badges per user (duplicates not allowed)

# Run the app

./mvnw spring-boot:run

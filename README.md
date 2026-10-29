# Student Management System - Spring Boot REST API

REST API version of the Student Management System, built with **Spring Boot**,
**Spring Data JPA (Hibernate)** and **MySQL**.
(The Core Java + JDBC console version is a separate project.)

## Tech stack
- Java 17, Spring Boot 3.5
- Spring Web (REST controllers)
- Spring Data JPA / Hibernate (ORM)
- Bean Validation (`@NotBlank`, `@Email`, `@Min`)
- MySQL, Maven

## Architecture (layered)
```
Client (Postman / browser)
   -> StudentController   (REST endpoints)
   -> StudentService      (business logic)
   -> StudentRepository   (Spring Data JPA, generates SQL)
   -> MySQL (students table)
```

## Project structure
```
src/main/java/com/student/
  StudentManagementApplication.java
  model/Student.java
  repository/StudentRepository.java
  service/StudentService.java
  controller/StudentController.java
src/main/resources/application.properties
pom.xml
```

## Setup
1. Install JDK 17+, Maven and MySQL.
2. Make sure MySQL is running. The database `student_db` is created automatically,
   and Hibernate creates the `students` table.
3. Set your MySQL password as an environment variable (never commit it):
   - Windows: `set DB_PASSWORD=your_password`
   - Linux/Mac: `export DB_PASSWORD='your_password'`
4. Run:
   ```
   mvn spring-boot:run
   ```
   The API starts at `http://localhost:8080`.

Eclipse: File > Import > Existing Maven Projects, then run
`StudentManagementApplication.java` as a Java Application
(add `DB_PASSWORD` in Run Configurations > Environment).

## API endpoints
| Method | URL | Description |
|---|---|---|
| POST | `/api/students` | Add a student |
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by id |
| GET | `/api/students/search?name=Aar` | Search by name |
| PUT | `/api/students/{id}` | Update a student |
| DELETE | `/api/students/{id}` | Delete a student |

## Sample requests and responses
*(Sample output. Replace with your own Postman screenshots after running.)*

**POST /api/students**
```json
{
  "name": "Aarav Sharma",
  "email": "aarav@example.com",
  "course": "Core Java",
  "age": 21
}
```
Response `201 Created`:
```json
{
  "id": 1,
  "name": "Aarav Sharma",
  "email": "aarav@example.com",
  "course": "Core Java",
  "age": 21
}
```

**GET /api/students**
```json
[
  { "id": 1, "name": "Aarav Sharma", "email": "aarav@example.com", "course": "Core Java", "age": 21 },
  { "id": 2, "name": "Priya Patil", "email": "priya@example.com", "course": "Advanced Java", "age": 22 }
]
```

**GET /api/students/search?name=priya**
```json
[
  { "id": 2, "name": "Priya Patil", "email": "priya@example.com", "course": "Advanced Java", "age": 22 }
]
```

**DELETE /api/students/2**
```json
{ "message": "Student deleted successfully" }
```

**Validation error** (POST with an invalid email and age 0), `400 Bad Request`:
```json
{
  "email": "Invalid email format",
  "age": "Age must be positive"
}
```

**Not found** (GET /api/students/99), `404 Not Found`:
```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Student not found with id 99"
}
```

## Spring Boot concepts used
- `@SpringBootApplication`, auto-configuration
- `@RestController`, `@RequestMapping`, `@GetMapping` / `@PostMapping` / `@PutMapping` / `@DeleteMapping`
- `@Service`, `@Repository`, constructor-based dependency injection
- `@Entity`, `@Table`, `@Id`, `@GeneratedValue` (JPA / Hibernate)
- Bean Validation with `@Valid`
- `application.properties` with environment variables for secrets

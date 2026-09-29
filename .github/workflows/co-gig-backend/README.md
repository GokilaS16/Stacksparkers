# Co-Gig Backend

Java 17 + Spring Boot + MySQL REST API for the Co-Gig frontend.

## Run

1. Install Java 17+ and Maven.
2. Install MySQL and make sure it is running.
3. Open `src/main/resources/application.properties`.
4. Replace `YOUR_MYSQL_PASSWORD` with your MySQL root password.
5. Run:
   - Windows: `mvnw.cmd spring-boot:run` (if Maven Wrapper exists) or `mvn spring-boot:run`
   - IDE: Run `CoGigApplication.java`
6. Backend runs at `http://localhost:8080`.

The database `cogig` is created automatically by the JDBC URL.

## API endpoints

POST `/api/auth/register`
POST `/api/auth/login`

GET `/api/services`
POST `/api/services`
GET `/api/services/{id}`

GET `/api/workers`
GET `/api/workers?skill=farm`
GET `/api/workers?location=Avadi`
POST `/api/workers`
GET `/api/workers/{id}`

GET `/api/bookings`
GET `/api/bookings?customerName=Goki`
POST `/api/bookings`
PATCH `/api/bookings/{id}/status?status=CONFIRMED`

## Important

This starter backend keeps passwords as plain text only to make local learning/testing easy.
Do NOT deploy this version to production. Before deployment, add Spring Security, BCrypt password hashing, JWT/session authentication, validation, authorization, and proper CORS configuration.

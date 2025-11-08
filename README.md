# Order Service (PostgreSQL)

## Requirements
- Java 20
- Maven
- PostgreSQL running on localhost:6673 with database `ordersdb`
  - username: postgres
  - password: postgres

## Run
1. Build: `mvn clean package`
2. Run: `java -jar target/order-service-1.0.0.jar`
3. Or: `mvn spring-boot:run` (for development)

## Endpoints (Postman)
- POST /api/orders
- GET /api/orders
- GET /api/orders/{id}
- PUT /api/orders/{id}
- DELETE /api/orders/{id}

## Postman
- Import `OrderService.postman_collection.json` and use the requests.

## Docker
- Build: `mvn clean package` then `docker build -t order-service:1.0 .`
- Run: `docker run -p 8080:8080 order-service:1.0` (standalone; configure DB separately)

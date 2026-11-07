# Project Context: Product Inventory Management API

## Root Packages
- **New code**: `com.rdllc.inventory` — the active project built per SPEC.md
- **Legacy code**: `com.fshtank` — reserved for refactored old project code; keep separate from new code

## Tech Stack
- Java 25 (Amazon Corretto 25)
- Spring Boot 4.1.2 (Spring Framework 7, Spring Security 7.1)
- PostgreSQL (local dev / Amazon RDS prod)
- JWT auth via jjwt 0.12.6
- Maven build

## Spring Boot 4.x / Spring Security 7 Notes
- Use `spring-boot-starter-webmvc` (NOT `spring-boot-starter-web`)
- Jackson 3.x uses `tools.jackson` packages (NOT `com.fasterxml.jackson`)
- CSRF is enabled by default — must explicitly disable for stateless JWT APIs
- Tests use `@MockitoBean` (not `@MockBean`) and `@AutoConfigureMockMvc`
- JUnit 5 Jupiter only

## API Endpoints
| Method | Path | Auth       | Description              |
|--------|------|------------|--------------------------|
| POST   | /api/auth/register | Public     | Register new user        |
| POST   | /api/auth/login    | Public     | Login, returns JWT       |
| GET    | /api/products      | USER/ADMIN | List products (paginated)|
| GET    | /api/products/{id} | USER/ADMIN | Get single product       |
| PUT    | /api/products/{id} | USER/ADMIN | Update product           |
| DELETE | /api/products/{id} | USER/ADMIN | Delete product           |
| GET    | /api/admin/stats   | ADMIN      | Aggregate stats          |

## Configuration
- Dev DB: `jdbc:postgresql://localhost:5432/inventory` (profiles: default)
- Prod DB: env vars via `application-prod.yml` (activate with `SPRING_PROFILES_ACTIVE=prod`)
- JWT secret: `JWT_SECRET` env var
- DDL auto: `update` in dev, `validate` in prod

## Build & Run
```bash
mvn clean package -DskipTests   # build
java -jar target/inventory-mgmt-api-0.0.1-SNAPSHOT.jar  # run
./mvnw spring-boot:run           # or via Maven plugin
```

## Deployment (EC2 Ubuntu 22.04)
See SPEC.md for systemd unit and deploy steps.

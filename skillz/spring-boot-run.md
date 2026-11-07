# Skill: spring-boot-run

Launch and verify the Spring Boot inventory management API.

## When to use
- After making code changes, start the server to test manually
- When debugging an endpoint or configuration issue
- To confirm a fix works at runtime

## Steps

1. **Check prerequisites**
   - Ensure PostgreSQL is running locally on port 5432
   - Ensure database `inventory` exists (create if needed): `createdb inventory`
   - Verify `JWT_SECRET` is set or use the default in application.yml

2. **Start the app**
   ```bash
   cd /Users/rickfisher/Development/inventory-mgmt-api
   ./mvnw spring-boot:run
   ```
   Wait for the log line: `Started InventoryApplication`

3. **Test an endpoint** (example)
   ```bash
   curl -s http://localhost:8080/api/products | python3 -m json.tool
   ```

4. **Stop the app**
   Press Ctrl+C when done

## Notes
- The server listens on port 8080 by default (configured in application.yml)
- Swagger UI is available at http://localhost:8080/swagger-ui.html (if springdoc-openapi is added)
- Use `SPRING_PROFILES_ACTIVE=prod` to activate production configuration

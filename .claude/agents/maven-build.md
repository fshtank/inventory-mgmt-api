# Skill: maven-build

Build the project, run tests, and package with Maven.

## When to use
- Building the project before deployment
- Running all tests or a specific test class
- Troubleshooting compilation errors

## Steps

### Clean build
```bash
cd /Users/rickfisher/Development/inventory-mgmt-api
./mvnw clean compile
```

### Run all tests
```bash
./mvnw test
```

### Package for deployment (skip tests)
```bash
./mvnw clean package -DskipTests
```

### Run a specific test
```bash
./mvnw test -Dtest=ProductControllerTest
```

### Check for dependency issues
```bash
./mvnw dependency:tree -Dincludes=*spring*
```

## Notes
- The project uses the wrapper (`./mvnw`) — do not require system Maven to be installed
- Spring Boot 4.1.2 parent is used; all dependency versions are managed by the parent POM
- Java 25 is required (Amazon Corretto 25 recommended for production)

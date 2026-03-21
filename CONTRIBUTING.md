# Contributing to E-Commerce Platform

Thank you for your interest in contributing! This is a Java/Spring Boot REST API project. Please read through this guide before opening a pull request.

---

## Table of contents

1. [Getting started](#getting-started)
2. [Development setup](#development-setup)
3. [Making changes](#making-changes)
4. [Code style](#code-style)
5. [Testing](#testing)
6. [Pull request process](#pull-request-process)
7. [Reporting issues](#reporting-issues)

---

## Getting started

### Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL 14+
- Git
- Postman (optional, for API testing)

### Fork and clone

```bash
# Fork this repo on GitHub, then:
git clone https://github.com/YOUR_USERNAME/E-Commerce-Platform.git
cd E-Commerce-Platform
git remote add upstream https://github.com/sakshi-nandgude/E-Commerce-Platform.git
```

---

## Development setup

### 1. Create the database

```sql
CREATE DATABASE ecommerce_db;
```

### 2. Configure application properties

Copy the example config and fill in your values:

```bash
cp src/main/resources/application.properties.example \
   src/main/resources/application.properties
```

Update with your local database credentials and a JWT secret:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=your_username
spring.datasource.password=your_password
jwt.secret=your_secret_key_min_32_characters
jwt.expiration=86400000
```

### 3. Build and run

```bash
mvn clean install
mvn spring-boot:run
# API available at http://localhost:8080
```

---

## Making changes

### Branch naming

Always branch off `main`. Use this naming convention:

| Type | Pattern | Example |
|------|---------|---------|
| Feature | `feature/short-description` | `feature/add-product-search` |
| Bug fix | `fix/short-description` | `fix/cart-null-pointer` |
| Docs | `docs/short-description` | `docs/update-api-endpoints` |
| Refactor | `refactor/short-description` | `refactor/order-service` |

```bash
git checkout -b feature/your-feature-name
```

### Commit messages

Follow [Conventional Commits](https://www.conventionalcommits.org/):

```
feat: add product search by category
fix: resolve null pointer in CartService
docs: update API endpoint table in README
refactor: extract OrderMapper from OrderService
test: add integration test for auth flow
```

---

## Code style

- Follow standard Java naming conventions (camelCase for methods/variables, PascalCase for classes)
- Keep methods focused — single responsibility per method
- Use constructor injection over field injection (`@Autowired` on fields is discouraged)
- Add Javadoc to all public service methods
- Avoid hardcoded strings — use constants or config properties
- All new endpoints must be secured with appropriate roles (see `SecurityConfig`)

**Package structure to follow:**

```
src/main/java/com/sakshi/ecommerce/
├── controller/     ← REST endpoints only, delegate to service
├── service/        ← Business logic
├── repository/     ← JPA interfaces only
├── entity/         ← JPA entities
├── dto/            ← Request/response objects
├── config/         ← Spring config classes
├── security/       ← JWT filter, UserDetails impl
└── exception/      ← Custom exceptions + global handler
```

---

## Testing

- Test new endpoints using Postman before submitting
- Include a Postman collection export if you add new routes
- Write unit tests for service layer methods where possible
- Run the full build before opening a PR:

```bash
mvn clean test
```

---

## Pull request process

1. Ensure your branch is up to date with `main`:
   ```bash
   git fetch upstream
   git rebase upstream/main
   ```
2. Run `mvn clean test` — all tests must pass
3. Open a PR against the `main` branch
4. Fill in the PR template (what changed, why, how to test)
5. Wait for review — address any requested changes

**PR checklist:**
- [ ] Code follows the package structure above
- [ ] New endpoints are secured and tested
- [ ] No secrets or credentials in the code
- [ ] `application.properties.example` updated if new config keys were added
- [ ] Build passes: `mvn clean test`

---

## Reporting issues

Please open a [GitHub Issue](https://github.com/sakshi-nandgude/E-Commerce-Platform/issues) and include:

- What you expected to happen
- What actually happened
- Steps to reproduce
- Java version, OS, and PostgreSQL version

---

**Maintained by [Sakshi Vijay Nandgude](https://github.com/sakshi-nandgude)**

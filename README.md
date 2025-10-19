# Auth Service

## Overview

The **Auth Service** is responsible for managing authentication and user authorization across the CUE Event Management System. It provides secure mechanisms for registration, login, token management (JWT + Refresh), and role-based access control. It serves as the main identity provider for the platform.

---

## Purpose

This service centralizes identity management and authentication logic to ensure consistent and secure access across all microservices. It handles:

* User registration and login.
* Access and refresh token generation.
* Token validation and rotation.
* Role-based authorization.
* Device and session tracking.

---

## Versions

| Component                                   | Version |
| ------------------------------------------- | ------- |
| **Java**                                    | 21      |
| **Spring Boot**                             | 3.5.4   |
| **Gradle**                                  | 8.14.3  |
| **Bancolombia Clean Architecture Scaffold** | 3.26.1  |

---

## Architecture

The Auth Service follows the **Bancolombia Clean Architecture Scaffold**, ensuring a modular, testable, and maintainable design.

```
auth-service/
├── applications/             # Application entry points and configurations
├── domain/                   # Core entities, value objects, and use cases
├── infrastructure/            # Repositories, adapters, and external integrations
├── build.gradle               # Gradle configuration
└── settings.gradle            # Project settings
```

### Layers

* **Domain:** Core business logic and models.
* **Use Cases:** Operations like login, registration, and token refresh.
* **Infrastructure:** Database, messaging, and AWS integrations.
* **Entry Points:** REST controllers exposing public endpoints.

---

## Environment Variables

The following environment variables are used by the **Auth Service**. All values should be configured via the `.env` file or your container environment.

```bash
# -----------------------------------
# Server Configuration
# -----------------------------------
SERVER_PORT=8080
SPRING_PROFILES_ACTIVE=dev

# -----------------------------------
# Database Configuration
# -----------------------------------
DB_URL=jdbc:mysql://mysql-auth:3306/auth_service?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
DB_USERNAME=auth_user
DB_PASSWORD=auth_password

# -----------------------------------
# JWT Configuration
# -----------------------------------
JWT_SECRET=your-jwt-secret-key
JWT_EXPIRATION_ACCESS=900000          # 15 minutes
JWT_EXPIRATION_REFRESH=604800000      # 7 days
INTERNAL_SECRET=your-internal-service-secret

# -----------------------------------
# Default Admin User (Bootstrap)
# -----------------------------------
ADMIN_EMAIL=admin@cue.edu.co
ADMIN_PASSWORD=admin123
ADMIN_FIRST_NAME=Admin
ADMIN_LAST_NAME=User

# -----------------------------------
# AWS Configuration
# -----------------------------------
AWS_REGION=us-east-1
AWS_ACCESS_KEY_ID=your-aws-access-key
AWS_SECRET_ACCESS_KEY=your-aws-secret-key
SNS_AUTH_TOPIC=arn:aws:sns:us-east-1:123456789012:auth-topic

# -----------------------------------
# Service Discovery
# -----------------------------------
EUREKA_URL=http://discovery-service:8761/eureka/

# -----------------------------------
# Logging Configuration
# -----------------------------------
LOGGING_LEVEL_ROOT=INFO
LOGGING_LEVEL_CO.EDU.CUE=DEBUG

# -----------------------------------
# CORS Configuration
# -----------------------------------
CORS_ALLOWED_ORIGINS=http://localhost:4200,http://localhost:3000
```

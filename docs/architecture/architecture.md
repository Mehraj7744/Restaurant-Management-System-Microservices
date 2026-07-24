# System Architecture

## Overview

The Restaurant Management System follows the Microservices Architecture pattern.

Each business capability is implemented as an independent Spring Boot service.

Current services:

- Eureka Server
- API Gateway
- Menu Service
- Order Service

Services communicate using REST APIs and OpenFeign.

---

## Architecture

```
                    Client
                       │
                       ▼
               API Gateway
                       │
      ┌────────────────┴────────────────┐
      │                                 │
      ▼                                 ▼
 Menu Service                     Order Service
      │                                 │
      └────────────OpenFeign────────────┘
                    │
              Eureka Server
                    │
                 MySQL
```

---

## Responsibilities

### Eureka Server

- Service Registration
- Service Discovery

### API Gateway

- Single Entry Point
- Routing
- Load Balancing

### Menu Service

- Category Management
- Menu Item Management

### Order Service

- Order Processing
- Total Calculation
- Menu Validation
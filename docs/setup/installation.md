# Installation Guide

## Prerequisites

- Java 21
- Maven
- MySQL 8+
- Eclipse IDE
- Git

---

## Clone Repository

```bash
git clone https://github.com/Mehraj7744/Restaurant-Management-System.git
```

---

## Create Databases

```sql
CREATE DATABASE restaurant_menu_db;
CREATE DATABASE restaurant_order_db;
```

---

## Run Services

Start in this order:

1. Eureka Server
2. API Gateway
3. Menu Service
4. Order Service

---

## Swagger

Menu

http://localhost:8082/swagger-ui/index.html

Order

http://localhost:8083/swagger-ui/index.html
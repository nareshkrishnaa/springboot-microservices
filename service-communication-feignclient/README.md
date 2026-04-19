# Inter service communication using Feign Client: Employee Service + Address Service

A simple **Spring Boot Microservices** demonstration showcasing inter-service communication using **Feign Client**.

## Architecture Overview

This project demonstrates a basic microservices architecture with two Spring Boot services communicating via REST using **Spring Cloud OpenFeign**.

### Block Diagram

![Feign Client Architecture](https://github.com/user-attachments/assets/bb2d8afd-b82a-46ab-b2e7-9b91c34f5196)

### Database Relationship
- `employee` table contains employee details + `address_id`
- `address` table contains address details with `employee_id`

The services communicate **via REST API** (not direct database join) to follow microservices best practices.

## How It Works

1. Client sends request to **Employee Service** → `GET /employee-service/employees/{id}`
2. Employee Service fetches basic employee details from its database
3. Using **Feign Client**, Employee Service calls **Address Service** to retrieve the address
4. Employee Service aggregates both responses and returns the combined data

## Services

### 1. Employee Service
- **Port**: `8080`
- **Context Path**: `/employee-service`
- **Responsibilities**:
  - Manages employee data
  - Uses Feign Client to fetch address from Address Service
  - Aggregates and returns combined response

### 2. Address Service
- **Port**: `8081`
- **Context Path**: `/address-service`
- **Responsibilities**:
  - Manages address data
  - Provides address by `employeeId`

## API Endpoints

### Employee Service (Public API)
```http
GET http://localhost:8080/employee-service/employees/{id}
```

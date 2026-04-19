# Microservices Example: Employee Service + Address Service

A simple **Spring Boot Microservices** demonstration showcasing inter-service communication using **Feign Client**.

## Architecture Overview

This project consists of two independent Spring Boot microservices:

- **Employee Service** (`localhost:8080`)
- **Address Service** (`localhost:8081`)

### Database Relationship
- `employee` table contains employee details with `address_id`
- `address` table contains address details with `employee_id`

Even though the tables have foreign key references, the services communicate **via REST API** (not direct database join) to demonstrate microservices architecture.

## How It Works

1. Client calls **Employee Service** → `GET /employee-service/employees/{id}`
2. Employee Service fetches employee details from its database
3. Using **Feign Client**, it calls **Address Service** to fetch the corresponding address
4. Employee Service aggregates both responses and returns a combined `EmployeeResponse`

## Services

### 1. Employee Service
- **Port**: `8080`
- **Context Path**: `/employee-service`
- **Main Functionality**: 
  - Handles employee data
  - Uses Feign Client to communicate with Address Service
  - Aggregates employee + address data

### 2. Address Service
- **Port**: `8081`
- **Context Path**: `/address-service`
- **Main Functionality**: 
  - Manages address data
  - Provides address details by employee ID

## API Endpoints

### Employee Service
```http
GET http://localhost:8080/employee-service/employees/{id}


<img width="801" height="881" alt="feign client " src="https://github.com/user-attachments/assets/bb2d8afd-b82a-46ab-b2e7-9b91c34f5196" />


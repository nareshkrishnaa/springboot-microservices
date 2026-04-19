Microservices Demo: Employee & Address Service (Spring Boot + Feign Client)
📌 Overview

This project demonstrates a simple microservices architecture using Spring Boot, where:

employee-service manages employee data
address-service manages address data
employee-service communicates with address-service using Feign Client
Final response is an aggregated Employee + Address object
🏗️ Services
1. Employee Service
Base URL: http://localhost:8080/employee-service
Port: 8080
Database Table: employee
Responsibility:
Fetch employee details
Call address-service via Feign Client
Aggregate employee + address response
2. Address Service
Base URL: http://localhost:8081/address-service
Port: 8081
Database Table: address
Responsibility:
Fetch address details based on employee_id
Return address response to employee-service
🔗 Inter-Service Communication
Feign Client Flow

employee-service uses Feign Client to call:

GET http://localhost:8081/address-service/address/{employeeId}
Flow Summary
Client calls Employee API
Employee Service fetches employee details from DB
Employee Service calls Address Service via Feign Client
Address Service returns address data
Employee Service aggregates response
Final response returned to client
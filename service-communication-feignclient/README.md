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
## Logs 
### employee-service 
```log
22:58:42 PM INFO  [employee-service] d.l.e.controller.EmployeeController - EmployeeController API called: http://localhost:8080/employee-service/employees/1
22:58:42 PM INFO  [employee-service] d.l.e.controller.EmployeeController - Calling EmployeeService.getEmployeeById() with id=1
22:58:42 PM INFO  [employee-service] d.l.e.service.EmployeeService - Getting Employee Details from EmployeeRepository
22:58:42 PM INFO  [employee-service] d.l.e.service.EmployeeService - Recieved Employee Details from EmployeeRepository : EmployeeResponse{id=1, name='Amiya', email='amiya@email.com', age='25', addressResponse=null}
22:58:42 PM INFO  [employee-service] d.l.e.service.EmployeeService - Requesting address-service for employee address details using Feign Client
22:58:42 PM DEBUG [employee-service] d.l.e.client.AddressClient - [AddressClient#getAddressByEmployeeId] ---> GET http://localhost:8081/address-service/address/1 HTTP/1.1
```
### address-service
```log
22:58:42 PM INFO  [address-service] d.l.a.controller.AddressController - 'AddressController API called': http://localhost:8081/address-service/address/1
22:58:42 PM INFO  [address-service] d.l.a.controller.AddressController - Requesting AddressService.findAddressByEmployeeId(1)
22:58:42 PM INFO  [address-service] d.l.a.service.AddressService - Getting Address Details from AddressRepo
22:58:42 PM INFO  [address-service] d.l.a.service.AddressService - Received Address Details from AddressRepo : AddressResponse{id=1, city='Bengaluru', state='Karnataka'}
22:58:42 PM INFO  [address-service] d.l.a.controller.AddressController - Returning addressResponse from AddressController : <200 OK OK,AddressResponse{id=1, city='Bengaluru', state='Karnataka'},[]> 
```
### employee-service 
```log
22:58:42 PM DEBUG [employee-service] d.l.e.client.AddressClient - [AddressClient#getAddressByEmployeeId] <--- HTTP/1.1 200 (351ms)
22:58:42 PM INFO  [employee-service] d.l.e.service.EmployeeService - Received employee address from address-service : AddressResponse{id=1, city='Bengaluru', state='Karnataka'}
22:58:42 PM INFO  [employee-service] d.l.e.service.EmployeeService - Aggregating Address response to Employee Response and sending the response to Employee Control Layer : EmployeeResponse{id=1, name='Amiya', email='amiya@email.com', age='25', addressResponse=AddressResponse{id=1, city='Bengaluru', state='Karnataka'}}
22:58:42 PM INFO  [employee-service] d.l.e.controller.EmployeeController - Received response from EmployeeService: EmployeeResponse{id=1, name='Amiya', email='amiya@email.com', age='25', addressResponse=AddressResponse{id=1, city='Bengaluru', state='Karnataka'}}
22:58:42 PM INFO  [employee-service] d.l.e.controller.EmployeeController - Returning Http response from Employee Controller : <200 OK OK,EmployeeResponse{id=1, name='Amiya', email='amiya@email.com', age='25', addressResponse=AddressResponse{id=1, city='Bengaluru', state='Karnataka'}},[]>
```



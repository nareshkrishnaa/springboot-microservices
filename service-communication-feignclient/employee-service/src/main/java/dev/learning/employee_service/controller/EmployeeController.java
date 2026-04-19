package dev.learning.employee_service.controller;

import dev.learning.employee_service.response.EmployeeResponse;
import dev.learning.employee_service.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/employees/{id}")
    private ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id, HttpServletRequest request) {
        String fullUrl = request.getRequestURL().toString();
        logger.info("EmployeeController API called: {}", fullUrl,id);
        //  Before service call
        logger.info("Calling EmployeeService.getEmployeeById() with id={}", id);
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        //  After service call
        logger.info("Received response from EmployeeService: {}",employee.toString());
        logger.info("Returning Http response from Employee Controller : {}",ResponseEntity.status(HttpStatus.OK).body(employee));
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }
}

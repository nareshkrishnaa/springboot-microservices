package dev.learning.employee_service.service;

import dev.learning.employee_service.client.AddressClient;
import dev.learning.employee_service.controller.EmployeeController;
import dev.learning.employee_service.entity.Employee;
import dev.learning.employee_service.repository.EmployeeRepository;
import dev.learning.employee_service.response.AddressResponse;
import dev.learning.employee_service.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private AddressClient addressClient;

    @Autowired
    private ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeResponse getEmployeeById(int id) {
        logger.info("Getting Employee Details from EmployeeRepository");
        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
        logger.info("Recieved Employee Details from EmployeeRepository : {}",employeeResponse.toString());

        logger.info("Requesting address-service for employee address details using Feign Client");
        ResponseEntity<AddressResponse> addressResponse = addressClient.getAddressByEmployeeId(id);
        logger.info("Received employee address from address-service : {}",addressResponse.getBody().toString());

        employeeResponse.setAddressResponse(addressResponse.getBody());
        logger.info("Aggregating Address response to Employee Response and sending the response to Employee Control Layer : {}",employeeResponse.toString());


        return employeeResponse;
    }


}

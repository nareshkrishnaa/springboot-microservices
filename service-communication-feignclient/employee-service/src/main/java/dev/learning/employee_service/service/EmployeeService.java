package dev.learning.employee_service.service;

import dev.learning.employee_service.client.AddressClient;
import dev.learning.employee_service.entity.Employee;
import dev.learning.employee_service.repository.EmployeeRepository;
import dev.learning.employee_service.response.AddressResponse;
import dev.learning.employee_service.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
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

    public EmployeeResponse getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);

        ResponseEntity<AddressResponse> addressResponse = addressClient.getAddressByEmployeeId(id);
        employeeResponse.setAddressResponse(addressResponse.getBody());

        return employeeResponse;
    }


}

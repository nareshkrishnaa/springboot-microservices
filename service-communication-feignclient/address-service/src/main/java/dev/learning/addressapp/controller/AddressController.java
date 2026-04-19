package dev.learning.addressapp.controller;


import dev.learning.addressapp.response.AddressResponse;
import dev.learning.addressapp.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;


@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);

    @GetMapping("/address/{employeeId}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("employeeId") int employeeId,HttpServletRequest request) {
        String fullUrl = request.getRequestURL().toString();
        logger.info("'AddressController API called': {}", fullUrl);
        logger.info("Requesting AddressService.findAddressByEmployeeId({})",employeeId);
        AddressResponse addressResponse = addressService.findAddressByEmployeeId(employeeId);
        logger.info("Returning addressResponse from AddressController : {} ",ResponseEntity.status(HttpStatus.OK).body(addressResponse));
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
    }

}
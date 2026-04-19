package dev.learning.addressapp.service;

import dev.learning.addressapp.controller.AddressController;
import dev.learning.addressapp.entity.Address;
import dev.learning.addressapp.repository.AddressRepo;
import dev.learning.addressapp.response.AddressResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ModelMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);

    public AddressResponse findAddressByEmployeeId(int employeeId) {
        logger.info("Getting Address Details from AddressRepo");
        Optional<Address> addressByEmployeeId = addressRepo.findAddressByEmployeeId(employeeId);
        AddressResponse addressResponse = mapper.map(addressByEmployeeId, AddressResponse.class);
        logger.info("Received Address Details from AddressRepo : {}",addressResponse.toString());
        return addressResponse;
    }

}

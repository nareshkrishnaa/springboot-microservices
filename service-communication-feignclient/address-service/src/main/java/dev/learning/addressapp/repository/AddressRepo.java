package dev.learning.addressapp.repository;

import dev.learning.addressapp.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {


    @Query(
            nativeQuery = true,
            value
                    = "SELECT id, city, state \n" +
                    "FROM address \n" +
                    "WHERE employee_id = :employeeId")
    Optional<Address> findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
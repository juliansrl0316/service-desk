package com.poli.servicedesk.repository.jpa;

import com.poli.servicedesk.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findByDocumentNumber(String document);
    List<Customer> findByStatus(String status);

}

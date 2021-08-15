package com.poli.servicedesk.repository.jpa;

import com.poli.servicedesk.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaEmployeeRepository extends JpaRepository<Employee, String> {

    Optional<Employee> findByDocumentNumber(String document);
    List<Employee> findByStatus(String status);
    List<Employee> findByRolId(String rolId);

}

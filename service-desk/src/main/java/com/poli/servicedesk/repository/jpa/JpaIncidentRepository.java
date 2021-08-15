package com.poli.servicedesk.repository.jpa;

import com.poli.servicedesk.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaIncidentRepository extends JpaRepository<Incident, String> {

    List<Incident> findByStatus(String status);
}

package com.poli.servicedesk.repository.jpa;

import com.poli.servicedesk.model.IncidentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaIncidentTypeRepository extends JpaRepository<IncidentType, String> {
}

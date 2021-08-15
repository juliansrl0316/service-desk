package com.poli.servicedesk.repository.jpa;

import com.poli.servicedesk.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRolRepository extends JpaRepository<Rol, String> {
}

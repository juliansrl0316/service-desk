package com.poli.servicedesk.repository.jpa;

import com.poli.servicedesk.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaEquipmentRepository extends JpaRepository<Equipment, String> {

    List<Equipment> findByStatus(String status);
}

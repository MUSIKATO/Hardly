package com.hardware.inventory.inventario_hardware.repository;

import com.hardware.inventory.inventario_hardware.entity.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware, Long> {
}
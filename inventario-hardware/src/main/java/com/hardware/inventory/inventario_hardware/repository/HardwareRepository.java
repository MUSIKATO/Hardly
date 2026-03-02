package com.hardware.inventory.inventario_hardware.repository;

import com.hardware.inventory.inventario_hardware.entity.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Capa de acceso a datos (Repository) para la entidad Hardware.
 *
 * Al extender JpaRepository, esta interfaz hereda automáticamente las
 * operaciones más comunes sobre la base de datos sin necesidad de escribir SQL:
 *   - save()       → guardar un registro
 *   - saveAll()    → guardar varios registros a la vez
 *   - findById()   → buscar por ID
 *   - findAll()    → obtener todos los registros
 *   - deleteById() → borrar por ID
 *   - deleteAll()  → borrar todos los registros
 *   - count()      → contar registros
 *
 * Parámetros de JpaRepository<Hardware, Long>:
 *   Hardware → entidad sobre la que opera
 *   Long     → tipo de dato del ID
 */
public interface HardwareRepository extends JpaRepository<Hardware, Long> {
}

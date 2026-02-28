package com.hardware.inventory.inventario_hardware.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data // Lombok genera Getter y Setter automáticamente [cite: 38]
@NoArgsConstructor
@AllArgsConstructor
public class Hardware {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;
    private Double precio;
}